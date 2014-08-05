package util;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import ui.GameFrame;

@SuppressWarnings("serial")
public class OpenPauseAction extends AbstractAction {

	private GameFrame gameFrame;
	
	public OpenPauseAction(GameFrame gameFrame) {
		this.gameFrame = gameFrame;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		gameFrame.togglePausePanel();
	}

}
