package controls;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import player.Player;


@SuppressWarnings("serial")
public class TurnRightAction extends AbstractAction {

	private Player player;

	public TurnRightAction(Player player) {
		this.player = player;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		//System.out.println("Right		Action");
		player.turnRight();
	}

}
