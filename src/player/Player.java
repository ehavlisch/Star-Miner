package player;

import ship.DefaultShip;
import ship.Ship;
import util.PlayerIcon;

public class Player {

	private Ship ship;

	private int money;
	
	private PlayerIcon playerIcon;

	public Player() {
		ship = new DefaultShip();
		playerIcon = new PlayerIcon(ship);
	}
	
	public void strafeLeft() {
		ship.strafeLeft();
	}
	
	public void strafeRight() {
		ship.strafeRight();
	}

	public void accelerate() {
		ship.accelerate();
	}

	public void decelerate() {
		ship.decelerate();
	}

	public void turnRight() {
		ship.turnRight();
	}

	public void turnLeft() {
		ship.turnLeft();
	}

	public Ship getShip() {
		return ship;
	}

	public int getMoney() {
		return money;
	}

	public void setMoney(int money) {
		this.money = money;
	}

	public void setShip(Ship ship) {
		this.ship = ship;
	}

	public PlayerIcon getPlayerIcon() {
		return playerIcon;
	}

	public void setPlayerIcon(PlayerIcon playerIcon) {
		this.playerIcon = playerIcon;
	}

	public void stabilize() {
		ship.stabilize();
	}
}
