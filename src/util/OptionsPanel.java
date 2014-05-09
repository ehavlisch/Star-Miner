package util;

import javax.swing.JPanel;

import main.SharedLocks;

@SuppressWarnings("serial")
public class OptionsPanel extends JPanel {
	private boolean collapsed = true;
	
	public OptionsPanel() {
		
	}
	
	public void showPanel() {
		if(collapsed == true) {
			SharedLocks.movementThreadLock.lock();
			collapsed = false;
		}
	}
	
	public void hidePanel() {
		if(collapsed == false) {
			SharedLocks.movementThreadLock.unlock();
			collapsed = true;
		}
	}
	
}
