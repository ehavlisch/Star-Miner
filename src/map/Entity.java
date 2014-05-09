package map;

import util.EntityIcon;

public class Entity {
	protected int mapX;
	protected int mapY;
	protected int radius;
	protected String iconSrc;
	protected EntityIcon entityIcon;
	
	protected boolean gravityWell = false;
	protected int gravityWellRadius;
	protected double gravityWellStrength;
	
	public String toString() {
		return "(" + mapX + ", " + mapY + ")";
	}

	public Entity(int x, int y, int radius) {
		this.mapX = x;
		this.mapY = y;
		this.radius = radius;
	}

	public String getIconSrc() {
		if (iconSrc == null) {
			System.out.println("Error: Null icon source");
		}
		return iconSrc;
	}

	public int getRadius() {
		return radius;
	}
	
	public int getLocalXPos(double playerX, int fWidth, int mapWidth) {
		int x = (int) (fWidth/2 + mapX - playerX - entityIcon.getWidth()/2);	
		
		if(x > 2*fWidth) {
			x -= mapWidth;
		}
		
		if(x < -fWidth) {
			x+= mapWidth;
		}
		return x;
	}
	
	public int getLocalYPos(double playerY, int fHeight, int mapHeight) {
		int y =  (int) (fHeight/2 + mapY - playerY - entityIcon.getHeight()/2);
		if(y > 2 * fHeight) {
			y -= mapHeight;
		}
		if(y < -fHeight) {
			y+= mapHeight;
		}
		return y;
	}
	
	public void updatePosition(int fWidth, int fHeight, double playerX, double playerY, int mapWidth, int mapHeight) {
		entityIcon.setBounds(getLocalXPos(playerX, fWidth, mapWidth), getLocalYPos(playerY, fHeight, mapHeight), entityIcon.getIconWidth(), entityIcon.getIconHeight());
	}

	public int getMapX() {
		return mapX;
	}

	public int getMapY() {
		return mapY;
	}

	public EntityIcon getEntityIcon() {
		return entityIcon;
	}
	
	public EntityIcon createIcon(double playerX, double playerY, int fWidth, int fHeight, int mapWidth, int mapHeight) {
		entityIcon = new EntityIcon(iconSrc);
		int newX = getLocalXPos(playerX, fWidth, mapWidth);		
		int newY = getLocalYPos(playerY, fHeight, mapHeight);
		entityIcon.setBounds(newX, newY, entityIcon.getIconWidth(), entityIcon.getIconHeight());
		return entityIcon;
	}
	
	public void purgeIcon() {
		entityIcon = null;
	}

	public boolean isGravityWell() {
		return gravityWell;
	}

	public int getGravityWellRadius() {
		return gravityWellRadius;
	}

	public double getGravityWellStrength() {
		return gravityWellStrength;
	}
}
