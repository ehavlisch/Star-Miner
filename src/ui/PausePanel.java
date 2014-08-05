package ui;

import javax.swing.JPanel;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.factories.FormFactory;

import javax.swing.JProgressBar;
import javax.swing.border.EtchedBorder;

import player.Player;
import ship.Ship;
import main.SharedLocks;

@SuppressWarnings("serial")
public class PausePanel extends JPanel {
	private boolean collapsed = true;
	private Player player;
	private JProgressBar shieldBar;
	private JProgressBar fuelBar;
	private JProgressBar healthBar;

	public PausePanel(Player player) {
		this.player = player;
		setBorder(new EtchedBorder(EtchedBorder.RAISED, null, null));
		setLayout(new FormLayout(new ColumnSpec[] {
				ColumnSpec.decode("200px"),},
			new RowSpec[] {
				RowSpec.decode("21px"),
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,}));
		
		JMenuBar menuBar = new JMenuBar();
		add(menuBar, "1, 1, fill, top");
		
		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		JMenuItem mntmNewGame = new JMenuItem("New Game");
		mnFile.add(mntmNewGame);
		
		healthBar = new JProgressBar();
		add(healthBar, "1, 3");
		
		fuelBar = new JProgressBar();
		add(fuelBar, "1, 5");
		
		shieldBar = new JProgressBar();
		add(shieldBar, "1, 7");
		
		this.setVisible(false);
	}
	
	public void showPanel() {
		if(collapsed == true) {
			SharedLocks.movementThreadLock.lock();
			collapsed = false;
			
			Ship ship = player.getShip();
			
			updateHealthBar(ship);
			updateFuelBar(ship);
			updateShieldBar(ship);
		}
	}
	
	public void hidePanel() {
		if(collapsed == false) {
			SharedLocks.movementThreadLock.unlock();
			collapsed = true;
		}
	}
	
	public void updateHealthBar(Ship ship) {
		healthBar.setMaximum(ship.getMaxHealth());
		healthBar.setValue(ship.getHealth());
	}
	
	public void updateShieldBar(Ship ship) {
		shieldBar.setMaximum(ship.getMaxShield());
		shieldBar.setValue(ship.getShield());
	}
	
	public void updateFuelBar(Ship ship) {
		fuelBar.setMaximum(ship.getMaxFuel());
		fuelBar.setValue((int) ship.getFuel());
	}

}
