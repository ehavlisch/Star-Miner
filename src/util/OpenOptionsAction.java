package util;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import main.UI;

@SuppressWarnings("serial")
public class OpenOptionsAction extends AbstractAction {

	private UI ui;
	
	public OpenOptionsAction(UI ui) {
		this.ui = ui;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		ui.toggleOptionsPanel();
	}

}
