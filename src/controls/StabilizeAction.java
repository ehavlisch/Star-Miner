package controls;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import player.Player;

@SuppressWarnings("serial")
public class StabilizeAction extends AbstractAction {
	private Player player;
	
	public StabilizeAction(Player player) {
		this.player = player;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		//System.out.println("Stabilize		Action");
		player.stabilize();
	}
}
