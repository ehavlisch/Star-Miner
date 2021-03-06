package entities;

import config.GlobalConfig;
import map.Entity;

public class DefaultSun extends Entity {
	public DefaultSun(int x, int y) {
		super(x, y, 25);
		iconSrc = "resources/images/sun.png";
		
		gravityWell = true;
		gravityWellRadius = 1000;
		gravityWellStrength = .01 / GlobalConfig.updatesPerSecond;
	}
}
