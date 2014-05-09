package controls;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import player.Player;


@SuppressWarnings("serial")
public class TurnLeftAction extends AbstractAction {
	private Player player;

	public TurnLeftAction(Player player) {
		this.player = player;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		//System.out.println("Left		Action");
		player.turnLeft();
	}
}
