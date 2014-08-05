package map;

import config.GlobalConfig;
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
	
	protected boolean solid = true;
	
	protected int localX;
	protected int localY;
	
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
	
	public int getLocalXPos(double playerX, int mapWidth) {
		int fWidth = GlobalConfig.gamePanelWidth;
		int x = (int) (fWidth/2 + mapX - playerX - entityIcon.getWidth()/2);	
		
		if(x > 2*fWidth) {
			x -= mapWidth;
		}
		
		if(x < -fWidth) {
			x+= mapWidth;
		}
		localX = x;
		return x;
	}
	
	public int getLocalYPos(double playerY, int mapHeight) {
		int fHeight = GlobalConfig.gamePanelHeight;
		int y =  (int) (fHeight/2 + mapY - playerY - entityIcon.getHeight()/2);
		if(y > 2 * fHeight) {
			y -= mapHeight;
		}
		if(y < -fHeight) {
			y+= mapHeight;
		}
		localY = y;
		return y;
	}
	
	public void updatePosition(double playerX, double playerY, int mapWidth, int mapHeight) {
		entityIcon.setBounds(getLocalXPos(playerX, mapWidth), getLocalYPos(playerY, mapHeight), entityIcon.getIconWidth(), entityIcon.getIconHeight());
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
	
	public EntityIcon createIcon(double playerX, double playerY, int mapWidth, int mapHeight) {
		entityIcon = new EntityIcon(iconSrc);
		int newX = getLocalXPos(playerX, mapWidth);		
		int newY = getLocalYPos(playerY, mapHeight);
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

	public int getLocalX() {
		return localX;
	}

	public int getLocalY() {
		return localY;
	}

	public boolean isSolid() {
		return solid;
	}
	
	
}
