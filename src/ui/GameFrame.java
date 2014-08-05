package ui;

import java.awt.BorderLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;

import player.Player;
import main.SharedLocks;
import config.GlobalConfig;
import controls.Controls;

@SuppressWarnings("serial")
public class GameFrame extends JFrame {

	boolean windowOpen = true;
	
	private PausePanel pausePanel;
	private GamePanel gamePanel;

	public GameFrame(Controls controls, Player player) {
		super();
		setTitle("Star Miner");
		setResizable(false);
		setBounds(GlobalConfig.gamePanelInitialX, GlobalConfig.gamePanelInitialY, 100, 100);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout());
		
		gamePanel = new GamePanel(controls, player, this);
		gamePanel.setVisible(true);
		gamePanel.setLayout(null);
		gamePanel.add(player.getPlayerIcon());

		mainPanel.add(gamePanel, BorderLayout.CENTER);
		
		this.getContentPane().add(mainPanel);
		this.pack();
		
		pausePanel = new PausePanel(player);
		
		mainPanel.add(pausePanel, BorderLayout.WEST);

		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent e) {
				if(!windowOpen) {
					SharedLocks.movementThreadLock.unlock();
				}
				windowOpen = true;
			}

			@Override
			public void windowDeactivated(WindowEvent e) {
				SharedLocks.movementThreadLock.lock();
				windowOpen = false;
			}
		});
	}
	
	public void togglePausePanel() {
		if(pausePanel.isVisible()) {
			pausePanel.hidePanel();
			pausePanel.setVisible(false);
		} else {
			pausePanel.showPanel();
			pausePanel.setVisible(true);
		}
	}

	public PausePanel getPausePanel() {
		return pausePanel;
	}

	public GamePanel getGamePanel() {
		return gamePanel;
	}
	
	
}
