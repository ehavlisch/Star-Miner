package controls;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import player.Player;

@SuppressWarnings("serial")
public class StrafeRightAction extends AbstractAction {
	private Player player;
	
	public StrafeRightAction(Player player) {
		this.player = player;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		//System.out.println("StrafeR		Action");
		player.strafeRight();
	}
}
