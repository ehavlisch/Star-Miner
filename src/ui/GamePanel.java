package ui;

import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;

import player.Player;

import util.OpenPauseAction;
import config.GlobalConfig;
import controls.Controls;
import controls.MoveBackAction;
import controls.MoveForwardAction;
import controls.StabilizeAction;
import controls.StrafeLeftAction;
import controls.StrafeRightAction;
import controls.TurnLeftAction;
import controls.TurnRightAction;

@SuppressWarnings("serial")
public class GamePanel extends JPanel implements KeyListener {

	private Player player;
	private int width;
	private int height;
	private GameFrame gameFrame;
	
	public GamePanel(Controls controls, Player player, GameFrame gameFrame) {
		addKeyListener(this);
		setFocusable(true);
		//requestFocusInWindow(true);
		this.gameFrame = gameFrame;
		this.player = player;
		mapActions();
		mapKeys(controls);
		
		this.width = GlobalConfig.gamePanelWidth;
		this.height = GlobalConfig.gamePanelHeight;
	
		this.setPreferredSize(new Dimension(width, height));
		this.setSize(new Dimension(width, height));
	}
	
	public void mapActions() {
		this.getActionMap().put("moveForward", new MoveForwardAction(player));
		this.getActionMap().put("moveBack", new MoveBackAction(player));
		this.getActionMap().put("turnRight", new TurnRightAction(player));
		this.getActionMap().put("turnLeft", new TurnLeftAction(player));
		this.getActionMap().put("strafeLeft", new StrafeLeftAction(player));
		this.getActionMap().put("strafeRight", new StrafeRightAction(player));
		this.getActionMap().put("stabilize", new StabilizeAction(player));
		this.getActionMap().put("openOptions", new OpenPauseAction(gameFrame));
	}
	
	public void mapKeys(Controls controls) {
		this.getInputMap().clear();
		this.getInputMap().put(controls.getForward(), "moveForward");
		this.getInputMap().put(controls.getForward2(), "moveForward");
		
		this.getInputMap().put(controls.getBack(), "moveBack");
		this.getInputMap().put(controls.getBack2(), "moveBack");
		
		this.getInputMap().put(controls.getTurnRight(), "turnRight");
		this.getInputMap().put(controls.getTurnRight2(), "turnRight");
		
		this.getInputMap().put(controls.getTurnLeft(), "turnLeft");
		this.getInputMap().put(controls.getTurnLeft2(), "turnLeft");
		
		this.getInputMap().put(controls.getStrafeLeft(), "strafeLeft");
		this.getInputMap().put(controls.getStrafeLeft2(), "strafeLeft");
		
		this.getInputMap().put(controls.getStrafeRight(), "strafeRight");
		this.getInputMap().put(controls.getStrafeRight2(), "strafeRight");
		
		this.getInputMap().put(controls.getStabilize(), "stabilize");
		this.getInputMap().put(controls.getStabilize2(), "stabilize");
		
		this.getInputMap().put(controls.getOpenOptions(), "openOptions");
		this.getInputMap().put(controls.getOpenOptions2(), "openOptions");

	}

	@Override
	public void keyTyped(KeyEvent e) {
		//System.out.println("Key Typed: " + e.getKeyChar());		
	}

	@Override
	public synchronized void keyPressed(KeyEvent e) {
		//System.out.println("Key Pressed: " + e.getKeyChar());
		
	}

	@Override
	public synchronized void keyReleased(KeyEvent e) {
		//System.out.println("Key Released: " + e.getKeyChar());
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

}

