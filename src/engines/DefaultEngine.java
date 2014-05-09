package engines;

import main.Constants;

public class DefaultEngine extends Engine {
	public DefaultEngine() {
		this.magnitude = .6 / Constants.updatesPerSecond;
		this.efficiency = 1.0;
		this.turning = Math.PI / 8.0;
		this.stabilizer = .6 / Constants.updatesPerSecond;
	}
}
