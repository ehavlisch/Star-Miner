package ship;

import java.util.List;

import map.Entity;

import physics.Vector;

import engines.Engine;

public class Ship {

	protected Object actionLock = new Object();
	protected boolean turnedLeft;
	protected boolean turnedRight;
	protected boolean accelerated;
	protected boolean decelerated;
	protected boolean strafeLefted;
	protected boolean strafeRighted;
	protected boolean stabilized;

	protected Object positionLock = new Object();
	protected double xPos;
	protected double yPos;
	
	protected double lastGoodX;
	protected double lastGoodY;

	protected Object headingLock = new Object();
	protected double heading;
	protected Object velocityLock = new Object();
	protected Vector velocity = new Vector(0, 0);
	
	protected int radius;

	protected Engine engine;
	protected List<Object> cargo;

	protected double mass;
	
	protected String iconSrc;

	public Engine getEngine() {
		return engine;
	}

	public void setEngine(Engine engine) {
		this.engine = engine;
	}

	public List<Object> getCargo() {
		return cargo;
	}

	public void setCargo(List<Object> cargo) {
		this.cargo = cargo;
	}

	/*
	 * Concern: Double maximum size is about 9.0e309 - Is this remotely possible
	 * to hit? On a bordered field, no. On a looping empty field and hours, yes.
	 * It will give the x position as infinity... Once at infinity, it cannot
	 * decrease to a real number again. Maybe do something strange like warp to
	 * random location. Or space police, stopping instantly after going high speeds can
	 * apparently cause huge damage, so illegal to go super fast?
	 * 
	 * Also upping the updates per second means each second you are going updatesPerSecond * your speed. Around 120 * 3 (360) the map fails to load things properly anymore
	 * Probably occurs regardless of updatesPerSecond once you reach a certain speed. 360 is not perfectly accurate. More testing is needed.
	 */

	public void checkPosition(int width, int height) {
		double widthD = (double) width;
		double heightD = (double) height;

		synchronized (positionLock) {
			while (xPos > widthD) {
				xPos -= widthD;
			}

			while (xPos < 0) {
				xPos += widthD;
			}

			while (yPos > heightD) {
				yPos -= heightD;
			}

			while (yPos < 0) {
				yPos += heightD;
			}
		}
		//System.out.println("X: " + xPos + " Y: " + yPos + " Heading: " + Math.toDegrees(heading) + ".");
	}

	public void move(int updatesPerSecond) {
		synchronized (actionLock) {
			accelerated = false;
			decelerated = false;
			turnedLeft = false;
			turnedRight = false;
			strafeRighted=false;
			strafeLefted=false;
			stabilized=false;

			double xDis = 0.0;
			double yDis = 0.0;
			synchronized (velocityLock) {
				xDis = velocity.getxMag() * updatesPerSecond;
				yDis = velocity.getyMag() * updatesPerSecond;
			}
			synchronized (positionLock) {
				xPos += xDis;
				yPos += yDis;
			}
			//System.out.println(velocity.getxMag());
			// checkPos(450, 300);
			// System.out.println("X: " + xPos + " Y: " + yPos + " Heading: " +
			// Math.toDegrees(heading) + ".");
		}
	}

	public void accelerate() {
		synchronized (actionLock) {
			if (!accelerated) {
				accelerated = true;
				synchronized (velocityLock) {
					double xMag = Math.cos(heading) * engine.getMagnitude();
					double yMag = Math.sin(heading) * engine.getMagnitude();

					this.velocity = velocity.add(new Vector(xMag, yMag));
				}
			}
		}
	}

	public void decelerate() {
		synchronized (actionLock) {
			if (!decelerated) {
				decelerated = true;
				synchronized (velocityLock) {
					double xMag = -Math.cos(heading) * engine.getMagnitude();
					double yMag = -Math.sin(heading) * engine.getMagnitude();

					this.velocity = velocity.add(new Vector(xMag, yMag));
				}
			}
		}
	}
	
	public void strafeLeft() {
		synchronized(actionLock) {
			if(!strafeLefted) {
				strafeLefted = true;
				synchronized (velocityLock) {
					double xMag = -Math.cos(heading + Math.PI/2) * engine.getMagnitude()/2;
					double yMag = -Math.sin(heading + Math.PI/2) * engine.getMagnitude()/2;

					this.velocity = velocity.add(new Vector(xMag, yMag));
				}
			}
		}
	}
	
	public void strafeRight() {
		synchronized(actionLock) {
			if(!strafeRighted) {
				strafeRighted = true;
				synchronized (velocityLock) {
					double xMag = -Math.cos(heading - Math.PI/2) * engine.getMagnitude()/2;
					double yMag = -Math.sin(heading - Math.PI/2) * engine.getMagnitude()/2;

					this.velocity = velocity.add(new Vector(xMag, yMag));
				}
			}
		}
	}
	
	public void stabilize() {
		synchronized(actionLock) {
			if(!stabilized) {
				stabilized = true;
				synchronized (velocityLock) {
					this.velocity.stabilize(engine.getStabilizer());
				}
			}
		}
	}

	public void turnRight() {
		synchronized (actionLock) {
			if (!turnedRight) {
				turnedRight = true;
				synchronized (headingLock) {
					heading += engine.getTurning();
					if (heading < 0) {
						heading += Math.PI * 2;
					}
				}
			}
		}
	}

	public void turnLeft() {
		synchronized (actionLock) {
			if (!turnedLeft) {
				turnedLeft = true;
				synchronized (headingLock) {
					heading -= engine.getTurning();
					if (heading > Math.PI * 2) {
						heading -= Math.PI * 2;
					}
				}
			}
		}
	}

	public double getHeading() {
		return heading;
	}

	public double getxPos() {
		return xPos;
	}

	public double getyPos() {
		return yPos;
	}

	public boolean collision(Entity e, double playerX, double playerY, int updatesPerSecond, int frameW, int frameH, int mapWidth, int mapHeight) {
		//TODO this fails if the entity icon is rotated and the width is no longer the diameter of the circle, should also be an int
		double entityR = e.getRadius();
		
		int entityX = (int) (e.getLocalXPos(xPos, frameW, mapWidth) + entityR);
		int entityY = (int) (e.getLocalYPos(yPos, frameH, mapHeight) + entityR);
		
//		System.out.println("Player at: (" + frameW/2 + ", " + frameH/2 + ")");
//		System.out.println("Entity at: (" + entityX + ", " + entityY + ")");
		
		// Find the distance between the center of the drawn icons, not their X, Y position in the map 
		// otherwise collisions on the map edge won't trigger collisions
		double dist = Math.sqrt((entityX - frameW/2)*(entityX - frameW/2) + (entityY - frameH/2)*(entityY - frameH/2));
		//System.out.println("dist: " + dist + " < " + entityR + " " + radius);
		double range = entityR + radius;
	
		if(dist <= range / 1.2) {
			// Severe overlap - bounce the player somewhere
			System.out.println("Severe overlap!");
			synchronized(positionLock) {
				xPos = lastGoodX;
				yPos = lastGoodY;
			}
			synchronized(velocityLock) {
				velocity.scale(0.9);
			}
			return true;
		} else if(dist <= range) {
			double collisionX = (entityX * entityR + frameW/2 * radius ) / (entityR + radius);
			double collisionY = (entityY * entityR + frameH/2 * radius ) / (entityR + radius);
			
			double slope = 0;
			if(entityX - collisionX != 0) {
				slope = ( entityY - collisionY) / (entityX - collisionX);
			} else {
				slope = Double.POSITIVE_INFINITY;
			}
			
			double pSlope = 0;
			
			if(slope == 0) {
				pSlope = Double.POSITIVE_INFINITY;
			} else if(Double.isInfinite(slope)) {
				pSlope = 0;
			} else {
				pSlope = - 1 / slope;
			}
			
//			System.out.println("Slope: " + slope);
//			System.out.println("pSlope: " + pSlope);
			synchronized (velocityLock) {
				this.velocity = this.velocity.reflect(pSlope, slope);
				this.velocity.scale(0.9);
			}
			return true;
		} else if(e.isGravityWell() && dist <= e.getGravityWellRadius()) {
			//System.out.println("Gravity Well");
			double xDiff = Math.abs(entityX - frameW/2);
			double yDiff = Math.abs(entityY - frameH/2);
			//System.out.println(xDiff + ", " + yDiff);
			double diffXum = xDiff + yDiff;
			double distScale =  1 - dist / e.getGravityWellRadius();
			double xPullMag = ( xDiff / diffXum ) * e.getGravityWellStrength() * distScale;
			double yPullMag = ( yDiff / diffXum ) * e.getGravityWellStrength() * distScale;
			if(entityX < frameW/2) xPullMag = -xPullMag;
			if(entityY < frameH/2) yPullMag = -yPullMag;
			Vector pullVector = new Vector(xPullMag, yPullMag);
			//System.out.println("<" + pullVector.getxMag() + ", " + pullVector.getyMag() + "> Scale: " + distScale);
			synchronized (velocityLock) {
				this.velocity = this.velocity.add(pullVector);
			}
		}
		return false;
	}

	public String getIconSrc() {
		return iconSrc;
	}

	public void setLastGood() {
		lastGoodX = xPos;
		lastGoodY = yPos;
	}
}
