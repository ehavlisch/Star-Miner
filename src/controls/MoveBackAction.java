package controls;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import player.Player;


@SuppressWarnings("serial")
public class MoveBackAction extends AbstractAction {

	private Player player;
	
	public MoveBackAction(Player player) {
		this.player = player;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		//System.out.println("Back		Action");
		player.decelerate();
	}

}
