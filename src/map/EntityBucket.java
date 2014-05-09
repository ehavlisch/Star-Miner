package map;

import java.util.ArrayList;
import java.util.List;

public class EntityBucket {
	private List<Entity> entities;
	private int largestDiameter;
	
	public String toString() {
		return "Largest: " + largestDiameter + " " + entities;
	}
	
	public void addEntity(Entity entity) {
		if(entities == null) {
			entities = new ArrayList<Entity>();
		}
		
		if(entity.getRadius() > largestDiameter) {
			largestDiameter = entity.getRadius();
		}
		
		entities.add(entity);
		//System.out.println(this.toString());
	}

	public List<Entity> getEntities() {
		return entities;
	}

	public int getLargestDiameter() {
		return largestDiameter;
	}
}
