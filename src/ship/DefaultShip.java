package ship;

import java.util.ArrayList;

import engines.DefaultEngine;

public class DefaultShip extends Ship {
	public DefaultShip() {
		this.engine = new DefaultEngine();
		this.cargo = new ArrayList<Object>(1);
		this.radius = 25;
		this.iconSrc = "resources/images/ship.png";
	}
}
