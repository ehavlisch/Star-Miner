package controls;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import player.Player;


@SuppressWarnings("serial")
public class MoveForwardAction extends AbstractAction {

	private Player player;
	
	public MoveForwardAction(Player player) {
		this.player = player;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		//System.out.println("Forward		Action");
		player.accelerate();
	}

}
