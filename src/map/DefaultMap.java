package map;

import java.util.ArrayList;
import java.util.List;

import entities.*;

public class DefaultMap extends Map {

	public DefaultMap() {
		super();
		generateMap();
	}
	
	@Override
	public void generateMap() {
		List<Entity> entityList = new ArrayList<Entity>();
//		entityList.add(new DefaultAsteroid(99900, 99900));
//		entityList.add(new DefaultAsteroid(100,100));
//		entityList.add(new DefaultAsteroid(0, 100));
//		entityList.add(new DefaultAsteroid(100, 0));
//		entityList.add(new DefaultAsteroid(120, 5));
		entityList.add(new DefaultAsteroid(99999, 0));
		
//		for(int i = 300; i < 100000; i += 500) {
//			for(int j = 300; j < 100000; j += 400) {
//				entityList.add(new DefaultAsteroid(i, j));
//			}
//		}

		
		for(Entity e : entityList) {
			addEntityToMap(e);
		}
		
		//addLargeEntityToMap(new DefaultSun(500, 0));

	}
}
