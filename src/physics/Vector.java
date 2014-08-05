package physics;

public class Vector {
	private double xMag;
	private double yMag;

	public Vector(double xMag, double yMag) {
		this.xMag = xMag;
		this.yMag = yMag;
	}

	public Vector add(Vector vector) {
		return new Vector(vector.getxMag() + xMag, vector.getyMag() + yMag);
	}

	public double getxMag() {
		return xMag;
	}

	public void setxMag(double xMag) {
		this.xMag = xMag;
	}

	public double getyMag() {
		return yMag;
	}

	public void setyMag(double yMag) {
		this.yMag = yMag;
	}

	public double getAngle() {
		return Math.atan(xMag / yMag);
	}

	public double getMagnitude() {
		return Math.sqrt(xMag * xMag + yMag * yMag);
	}

	public void stabilize(double magnitude) {
		
		double xStab = 0.0;
		double yStab = 0.0;
		
		if(xMag != 0) {
			xStab = -xMag / (Math.abs(xMag) + Math.abs(yMag)) * magnitude;
		}
		if(yMag != 0) {
			yStab = -yMag / (Math.abs(xMag) + Math.abs(yMag)) * magnitude;
		}
		
		if (Math.abs(xStab) > Math.abs(xMag)) {
			xMag = 0.0;
		} else {
			xMag += xStab;
		}
		if (Math.abs(xStab) > Math.abs(xMag)) {
			yMag = 0.0;
		} else {
			yMag += yStab;
		}
	}

	public void scale(double scale) {
		this.xMag *= scale;
		this.yMag *= scale;
	}

	public Vector reflect(double wallSlope, double normalSlope) {
		if (Double.isInfinite(wallSlope)) {
			xMag = -xMag;
		} else if (wallSlope == 0) {
			yMag = -yMag;
		} else {
			double theta = Math.atan(normalSlope);
			Vector normalVector = new Vector(Math.cos(theta), Math.sin(theta));
			return this.add(normalVector.scalar(-2 * this.dot(normalVector)));
		}
		return this;
	}

	public double dot(Vector v) {
		return (xMag * v.getxMag()) + (yMag * v.getyMag());
	}

	public Vector scalar(double scale) {
		return new Vector(xMag * scale, yMag * scale);
	}
}
