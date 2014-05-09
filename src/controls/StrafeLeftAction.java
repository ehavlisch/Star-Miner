package controls;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import player.Player;

@SuppressWarnings("serial")
public class StrafeLeftAction extends AbstractAction {
	private Player player;
	
	public StrafeLeftAction(Player player) {
		this.player = player;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		//System.out.println("StrafeL		Action");
		player.strafeLeft();
	}
}
