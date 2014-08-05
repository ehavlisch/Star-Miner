package engines;

public class DefaultEngine extends Engine {
	public DefaultEngine() {
		this.force = 100;
		this.efficiency = 1.0;
		this.turning = Math.PI / 8.0;
		this.stabilizer = 80;
		this.mass = 100;
	}
}
