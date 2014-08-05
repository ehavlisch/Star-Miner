package ship;

import java.util.ArrayList;

import engines.DefaultEngine;

public class DefaultShip extends Ship {
	public DefaultShip() {
		this.engine = new DefaultEngine();
		this.cargo = new ArrayList<Object>(1);
		this.radius = 25;
		this.iconSrc = "resources/images/ship.png";

		this.fuel = 500;
		this.maxFuel = 1000;

		this.health = 900;
		this.maxHealth = 1000;

		this.shield = 200;
		this.maxShield = 200;
		
		this.mass = 1000;
		
	}
}
