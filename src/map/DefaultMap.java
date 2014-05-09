package map;

import java.util.ArrayList;
import java.util.List;

import entities.DefaultAsteroid;
import entities.DefaultSun;

public class DefaultMap extends Map {

	public DefaultMap() {
		super();
		generateMap();
	}
	
	@Override
	public void generateMap() {
		List<Entity> entityList = new ArrayList<Entity>();
		entityList.add(new DefaultAsteroid(99900, 99900));
		entityList.add(new DefaultAsteroid(100,100));
		entityList.add(new DefaultAsteroid(0, 100));
		entityList.add(new DefaultAsteroid(100, 0));

		
		for(Entity e : entityList) {
			addEntityToMap(e);
		}
		
		addLargeEntityToMap(new DefaultSun(500, 0));

	}
}
