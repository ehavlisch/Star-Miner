package util;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

@SuppressWarnings("serial")
public class EntityIcon extends JLabel {
	
	// In case we ever want to rotate the icon (idle animation)
	private int iconWidth;
	private int iconHeight;
	
	public EntityIcon(String iconSrc) {
		super();
		BufferedImage iconBI = null;
		try {
			iconBI = ImageIO.read(new File(iconSrc));
		} catch (IOException e) {
			e.printStackTrace();
			iconBI = null;
		}
		if(iconBI != null) {
			ImageIcon imageIcon = new ImageIcon(iconBI);
			RotatedIcon ri = new RotatedIcon(imageIcon, 0);
			setIcon(ri);
			iconWidth = iconBI.getWidth();
			iconHeight = iconBI.getHeight();
		}
	}

	public int getIconWidth() {
		return iconWidth;
	}

	public int getIconHeight() {
		return iconHeight;
	}
}
