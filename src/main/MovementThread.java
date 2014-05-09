package main;

import java.util.ArrayList;
import java.util.List;

import player.Player;
import util.EntityIcon;
import util.GamePanel;
import map.Entity;
import map.Map;

public class MovementThread extends Thread {
	
	private int updatesPerSecond;
	private Player player;
	private Map map;
	private GamePanel gamePanel;
	
	private volatile boolean running = true;
	
	private List<Entity> drawnIcons;
	
	public MovementThread(int updatesPerSecond, Player player, Map map, GamePanel gamePanel) {
		this.updatesPerSecond = updatesPerSecond;
		this.player = player;
		this.map = map;
		this.gamePanel = gamePanel;
		drawnIcons = new ArrayList<Entity>();
	}
	
	public void run() {
		running = true;
		long sleepTime = 1000 / updatesPerSecond;
		while(running) {
			
			try {
				Thread.sleep(sleepTime);
				SharedLocks.movementThreadLock.lock();
				gamePanel.requestFocus();
				/*
				 * Draw Entities
				 */
				
				double playerX = player.getShip().getxPos();
				double playerY = player.getShip().getyPos();
				List<Entity> visibleEntities = map.getVisible(playerX, playerY, gamePanel.getWidth(), gamePanel.getHeight());				
				List<Entity> noLongerVisible = new ArrayList<Entity>();
				
				boolean collision = false;
				
				for(Entity e : drawnIcons) {
					if(visibleEntities.contains(e)) {
						visibleEntities.remove(e);
						e.updatePosition(gamePanel.getWidth(), gamePanel.getHeight(), playerX, playerY, map.getWidth(), map.getHeight());

						collision = player.getShip().collision(e, playerX, playerY, updatesPerSecond, gamePanel.getWidth(), gamePanel.getHeight(), map.getWidth(), map.getHeight()) || collision;
					} else {
						noLongerVisible.add(e);
					}
				}
				if(!collision) {
					player.getShip().setLastGood();
				}
				for(Entity e : visibleEntities) {
					EntityIcon eIcon = e.createIcon(playerX, playerY, gamePanel.getWidth(), gamePanel.getHeight(), map.getWidth(), map.getHeight());
					gamePanel.add(eIcon);
					drawnIcons.add(e);
				}
				for(Entity e : noLongerVisible) {
					drawnIcons.remove(e);
					e.purgeIcon();
				}
				
				/*
				 * Player Movement
				 */
				player.getShip().move(updatesPerSecond);
				player.getShip().checkPosition(map.getWidth(), map.getHeight());
				player.getPlayerIcon().updateHeading(player.getShip().getHeading());
				SharedLocks.movementThreadLock.unlock();		
			} catch (InterruptedException e) {
				e.printStackTrace();
				running = false;
			}
		}
	}
}
