package util;

import java.awt.EventQueue;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

import ship.Ship;

@SuppressWarnings("serial")
public class PlayerIcon extends JLabel {

	private ImageIcon imageIcon;
	private double heading;
	// these should be more dynamic to allow the frame to be resized
	// create a new player icon if the window is resized, we will already have to redrawn the screen
	private int fHeight;
	private int fWidth;
	
	private RotatedIcon ri;	
	
	public PlayerIcon(Ship ship, int fWidth, int fHeight) {
		super();
		this.heading = Math.PI/2;
		this.fWidth = fWidth;
		this.fHeight = fHeight;
		
		BufferedImage iconBI = null;
		try {
			iconBI = ImageIO.read(new File(ship.getIconSrc()));
		} catch (IOException e) {
			e.printStackTrace();
			iconBI = null;
		}
		if(iconBI != null) {
			imageIcon = new ImageIcon(iconBI);
			ri = new RotatedIcon(imageIcon, heading);
			setIcon(ri);
		}
		this.setBounds((fWidth - ri.getIconWidth()) / 2 , (fHeight - ri.getIconHeight()) / 2, ri.getIconWidth(), ri.getIconHeight());
	}
	
	public void updateHeading(double newHeading) {
		if(newHeading != heading) {
			heading = newHeading;
			EventQueue.invokeLater(new Runnable() {

				@Override
				public void run() {
					ri = new RotatedIcon(imageIcon, heading);
					setIcon(ri);
					int width = ri.getIconWidth();
					int height = ri.getIconHeight();
//					System.out.println("(" + (fWidth - width) / 2 + ", " + (fHeight - height) / 2 + ")");
//					System.out.println(width + " x " + height);
					setBounds((fWidth - width) / 2 , (fHeight - height) / 2, width, height);
				}
				
			});
		}
	}
}
