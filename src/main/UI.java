package main;

import map.DefaultMap;
import map.Map;
import controls.Controls;
import ui.*;
import player.Player;

public class UI {

	/*
	 * UI Components
	 */
	public GameFrame gameFrame;
	
	private Controls controls;
	private Player player;
	private Map map;
	
	private MovementThread movementThread;


	/**
	 * Create the application.
	 */
	public UI() {
		controls = new Controls();
		player = new Player();
		map = new DefaultMap();
		
		initialize();
		startMovementThread();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		gameFrame = new GameFrame(controls, player);
	}
	
	private void startMovementThread() {		
		movementThread = new MovementThread(player, map, gameFrame.getGamePanel());
		movementThread.start();
	}
}
