package util;

import java.util.ArrayList;
import java.util.List;

import config.GlobalConfig;
import physics.Vector;
import ship.Ship;
import map.Entity;
import map.Map;

public class CollisionBox {
	private double initialX;
	private double initialY;
	
	private double finalX;
	private double finalY;

	private Vector vector;
	
	private Ship ship;
	private Map map;
	
	private double collisionX;
	private double collisionY;
	private Vector collisionVector;
	
	public CollisionBox(double initialX, double initialY, double finalX, double finalY, Vector vector, Ship ship, Map map) {
		this.initialX = initialX;
		this.initialY = initialY;
		this.finalX = finalX;
		this.finalY = finalY;
		this.vector = vector;	
		this.ship = ship;
		this.map = map;
	}
	
	public boolean collision(List<Entity> visibleEntities) {
		if(initialX == finalX && initialY == finalY) {
			return false;
		}
		
		List<Entity> entities = new ArrayList<Entity>();
		
		for(Entity e : visibleEntities) {
			if(!e.isSolid()) {
				entities.add(e);
			}
		}

		for(int i = 0; i < entities.size(); i++) {	
			
		}
		
		vector.getAngle();
				
		//rectangle + half circle cut
		
		//if any collisions, order by distance from original position
		
		//calculate reflection off first collision
		
		//call recursively to determine if there's any future collisions
		
		return entities.size() > 0;
	}

	public double getCollisionX() {
		return collisionX;
	}

	public double getCollisionY() {
		return collisionY;
	}

	public Vector getCollisionVector() {
		return collisionVector;
	}	
}
