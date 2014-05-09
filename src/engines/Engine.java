package engines;

import physics.Vector;

public class Engine {
	protected double turning;
	protected double magnitude;
	protected double efficiency;
	protected double stabilizer;

	public Vector genVector(double heading) {
		double xMag = magnitude * Math.sin(heading);
		double yMag = magnitude * Math.cos(heading);

		if (heading > Math.PI / 2 && heading < 3 * Math.PI / 2) {
			xMag = -xMag;
		}

		if (heading > Math.PI && heading < Math.PI * 2) {
			yMag = -yMag;
		}

		return new Vector(xMag, yMag);
	}

	public double getMagnitude() {
		return magnitude;
	}

	public void setMagnitude(double magnitude) {
		this.magnitude = magnitude;
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
}
