package engines;

import physics.Vector;

public class Engine {
	protected double turning;
	protected double force;
	protected double efficiency;
	protected double stabilizer;
	protected double mass;

	public Vector genVector(double heading) {
		double xMag = force * Math.sin(heading);
		double yMag = force * Math.cos(heading);

		if (heading > Math.PI / 2 && heading < 3 * Math.PI / 2) {
			xMag = -xMag;
		}

		if (heading > Math.PI && heading < Math.PI * 2) {
			yMag = -yMag;
		}

		return new Vector(xMag, yMag);
	}

	public double getForce() {
		return force;
	}

	public void setForce(double force) {
		this.force = force;
	}

	public double getEfficiency() {
		return efficiency;
	}

	public void setEfficiency(double efficiency) {
		this.efficiency = efficiency;
	}

	public double getTurning() {
		return turning;
	}

	public void setTurning(double turning) {
		this.turning = turning;
	}

	public double getStabilizer() {
		return stabilizer;
	}

	public void setStabilizer(double stabilizer) {
		this.stabilizer = stabilizer;
	}

	public double getMass() {
		return mass;
	}

	public void setMass(double mass) {
		this.mass = mass;
	}
	
	
}
