package map;

import java.util.ArrayList;
import java.util.List;

import config.GlobalConfig;

public abstract class Map {
	protected int bDivX = 1000;
	protected int bSizeX;
	protected int bDivY = 1000;
	protected int bSizeY;
	protected int width;
	protected int height;
	protected EntityBucket[][] buckets;
	
	protected EntityBucket largeEntities;

	public Map() {
		width = 100000;
		height = 100000;
		buckets = new EntityBucket[bDivX][bDivY];
		bSizeX = width/bDivX;
		bSizeY = height/bDivY;
		largeEntities = new EntityBucket();
	}	

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}
	
	private EntityBucket getBucket(int x, int y) {
		EntityBucket bucket = buckets[x][y];
		if(bucket == null) {
			bucket = new EntityBucket();
			buckets[x][y] = bucket;
		}
		return bucket;
	}
	
	public List<Entity> getVisible(double d, double f) {

		int rightTwo = 0;
		int botTwo = 0;
		
		int frameWidth = GlobalConfig.gamePanelWidth;
		int frameHeight = GlobalConfig.gamePanelHeight;
		
		ArrayList<EntityBucket> bucketList = new ArrayList<EntityBucket>();

		int left = (int) Math.floor((d - frameWidth/2) / bSizeX) - 1;
		int right = (int) Math.floor((d + frameWidth/2) / bSizeX) + 2;
		int top = (int) Math.floor((f - frameHeight/2) / bSizeY) - 1;
		int bot = (int) Math.floor((f + frameHeight/2) / bSizeY) + 2;
		
		//System.out.println("Original Buckets: " + left + " to " + right + " X " + top + " to " + bot + " ");
		
		//System.out.println("Before " + left + " < 0 || " + right + " > " + bDivX);
		if(left < 0 || right > bDivX) {
			//System.out.println("Left < 0 || right > bDivX");
			if(left < 0 && right > bDivX) {
				//Would require drawing duplicates of objects
				//System.out.println("SEVERE: Map too small horizontally for view - unhandled");
				//left = 0;
				//right = bDivX;
				return null;
			} else if(right > bDivX) {
				//System.out.println("Right > bDivX");
				rightTwo = right - bDivX;
				right = bDivX;
			} else if(left < 0) {
				//System.out.println("Left < 0");
				rightTwo = right;
				left += bDivX;
				right = bDivX;
			}
		}
		//System.out.println("Before " + top + " < 0 || " + bot + " > " + bDivY);
		if(top < 0 || bot > bDivY) {
			//System.out.println("True");
			if(top < 0 && bot > bDivY) {
				//Would require drawing duplicates of objects
				System.out.println("SEVERE: Map too small vertically for view - unhandled");
				//bot = bDivY;
				//top = 0;
				return null;
			} else if(bot > bDivY) {
				//System.out.println("bot > bDivY");
				botTwo = bot - bDivY;
				bot = bDivY;
			} else if(top < 0) {
				//System.out.println("top < 0");
				botTwo = bot;
				top += bDivY;
				bot = bDivY;
			}
		}
		
		if(rightTwo != 0) {
			//rightTwo++;
			if(botTwo != 0) {
				//botTwo++;
				//Tested right > bDivX, bot > bDivY
				//Tested right > bDivX, top < 0
				//Tested left < 0, top < 0
				//Tested left < 0, bot > bDivY
				//System.out.println("Buckets After Wrapping: 0 to " + rightTwo + " and 0 to " + botTwo + ".");
				//System.out.println("Buckets After Wrapping: 0 to " + rightTwo + " and " + top + " to " + bot + ".");
				bucketList.addAll(getBuckets(0, rightTwo, 0, botTwo));
				bucketList.addAll(getBuckets(0, rightTwo, top, bot));
				
				//System.out.println("Buckets After Wrapping: " + left + " to " + right + " and 0 to " + botTwo + ".");
				//System.out.println("Buckets After Wrapping: " + left + " to " + right + " and " + top + " to " + bot + ".");
				bucketList.addAll(getBuckets(left, right, 0, botTwo));
				bucketList.addAll(getBuckets(left, right,top, bot));
			} else {
				//Tested Left < 0 && right > bDivX
				//System.out.println("Buckets After botTwo == 0: 0 to " + rightTwo + " and " + top + " to " + bot + ".");
				//System.out.println("Buckets After botTwo == 0: " + left + " to " + right + " and " + top + " to " + bot + ".");
				bucketList.addAll(getBuckets(0, rightTwo, top, bot));
				bucketList.addAll(getBuckets(left, right, top, bot));
			}
		} else {
			if(botTwo != 0) {
				//botTwo++;
				//Tested bot > bDivY
				//Tested top < 0
				//System.out.println("Buckets After Wrapping: " + left + " to " + right + " and 0 to " + botTwo + ".");
				//System.out.println("Buckets After Wrapping: " + left + " to " + right + " and " + top + " to " + bot + ".");
				bucketList.addAll(getBuckets(left, right, 0, botTwo));
				bucketList.addAll(getBuckets(left, right, top, bot));
			} else {
				//System.out.println("Buckets After Wrapping: " + left + " to " + right + " and " + top + " to " + bot + ".");
				bucketList.addAll(getBuckets(left, right, top, bot));
			}
		}
		List<Entity> entities = new ArrayList<Entity>();
		
		if(largeEntities != null && largeEntities.getEntities() != null) {
			entities.addAll(this.largeEntities.getEntities());
		}
		
		//TODO Don't add entities beyond the frame to the visible buckets to save a bit more memory
		if(bucketList != null && bucketList.size() > 0) {
//			int leftEdge = (int) (d - frameWidth/2);
//			int rightEdge = (int) (d + frameWidth/2);
//			int topEdge = (int) (f - frameHeight/2);
//			int botEdge = (int) (f + frameHeight/2);
			
			//Check if visible using largest diameter in the entityBucket
			for(EntityBucket b : bucketList) {
				for(Entity e : b. getEntities()) {
//					int eXL = e.getX() - e.getRadius();
//					int eXR = e.getX() + e.getRadius();
//					int eYT = e.getY() - e.getRadius();
//					int eYB = e.getY() + e.getRadius();
//					if(eXL > leftEdge && eXR < rightEdge && eYT > topEdge && eYB < botEdge) {
						entities.add(e);
					//}
				}
			}
		}
		
		return entities;
	}
	
	private List<EntityBucket> getBuckets(int left, int right, int top, int bot) {
		ArrayList<EntityBucket> bucketList = new ArrayList<EntityBucket>();
		
		for(int i = left; i < right; i++) {
			for(int j = top; j < bot; j++) {
				if(buckets[i][j] != null) {
					bucketList.add(buckets[i][j]);
				}
			}
		}
		
		return bucketList;
	}
	
	private EntityBucket getBucketForLocation(int x, int y) throws Exception {
		if(x > width || x < 0 || y > height || y < 0) {
			throw new Exception("Entity Bucket Out of Bounds!");
		}
		
		int xBucket = (int) Math.floor(x / bSizeX);
		int yBucket = (int) Math.floor(y / bSizeY);
		
		return getBucket(xBucket, yBucket);
	}
	
	public void addEntityToMap(Entity entity) {
		EntityBucket targetEntityBucket = null;
		try {
			targetEntityBucket = getBucketForLocation(entity.getMapX(), entity.getMapY());
		} catch (Exception e) {
			e.printStackTrace();
			return;
		}
		targetEntityBucket.addEntity(entity);
	}
	
	public void addLargeEntityToMap(Entity e) {
		this.largeEntities.addEntity(e);
	}
	
	public abstract void generateMap();
}
