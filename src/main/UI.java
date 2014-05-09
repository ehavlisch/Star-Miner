package main;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

import map.DefaultMap;
import map.Map;

import controls.Controls;

import util.GamePanel;
import util.OptionsPanel;

import player.Player;

import javax.swing.border.EtchedBorder;

public class UI {
	
	/* 
	 * Change engine magnitudes to divide by updatesPerSecond otherwise you go way too fast
	 */
	private int updatesPerSecond = Constants.updatesPerSecond;

	/*
	 * UI Components
	 */
	public JFrame gameFrame;
	
	private JPanel mainPanel;
	private GamePanel gamePanel;
	private OptionsPanel optionsPanel;
	
	/*
	 * Save frameW, frameH, controls into user settings
	 */
//	private int frameW;
//	private int frameH;
	
	private Controls controls;
	private Player player;
	private Map map;
	
	private MovementThread movementThread;


	/**
	 * Create the application.
	 */
	public UI() {
		
		controls = new Controls();
		player = new Player(Constants.frameWidth, Constants.frameHeight);
		map = new DefaultMap();
		
		initialize();
		startMovementThread();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		gameFrame = new JFrame();
		gameFrame.setTitle("Star Miner");
		gameFrame.setResizable(false);
		gameFrame.setBounds(100, 100, 100, 100);
		gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout());
		
		gamePanel = new GamePanel(controls, player, Constants.frameWidth, Constants.frameHeight, this);
		gamePanel.setVisible(true);
		gamePanel.setLayout(null);
		gamePanel.add(player.getPlayerIcon());

		mainPanel.add(gamePanel, BorderLayout.CENTER);
		
		gameFrame.getContentPane().add(mainPanel);
		gameFrame.pack();
		
		optionsPanel = new OptionsPanel();
		optionsPanel.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, null));
		optionsPanel.setVisible(false);
		
		mainPanel.add(optionsPanel, BorderLayout.WEST);
		

	}
	
	private void startMovementThread() {
		
		movementThread = new MovementThread(updatesPerSecond, player, map, gamePanel);
		movementThread.start();
	}

	public void toggleOptionsPanel() {
		if(optionsPanel.isVisible()) {
			optionsPanel.hidePanel();
			optionsPanel.setVisible(false);
		} else {
			optionsPanel.showPanel();
			optionsPanel.setVisible(true);
		}
	}
}
