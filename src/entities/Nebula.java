package entities;

import map.Entity;

public class Nebula extends Entity {
	public Nebula(int x, int y) {
		super(x, y, 25);
		iconSrc = "resources/images/sun.png";
		solid = false;
	}
}
