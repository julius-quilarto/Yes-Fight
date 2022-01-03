import java.awt.*;
import javax.swing.*;

/**
 * Class to represent an obstacle to display
 * @author Julius Quilarto
 * @version 10/29/2021
 *
 */

public class Obstacle {
	
	private JLabel theImageDisplay;
	private JLabel theCollisionBox;
	private Rectangle rectangleCollisionBox;
	private ImageIcon theImageIcon;
	private Image theImage;
	
	/**
	 * Constructor for Obstacle class
	 * @param collisionBox integer array to determine placement of collision box and the size of collision box;
	 * 			first number is x-coordinate, second number is y-coordinate, third number is width, fourth number
	 * 			height
	 * @param imagePlacement integer array to determine placement of image and the size of image;
	 * 			first number is x-coordinate, second number is y-coordinate, third number is width, fourth number
	 * 			height
	 * @param imageFileName name of the file of the image
	 */
	
	public Obstacle(int[] collisionBox, int[] imagePlacement, String imageFileName) {
		this.theCollisionBox = new JLabel();
		this.theCollisionBox.setBounds(collisionBox[0], collisionBox[1], collisionBox[2], collisionBox[3]);
		this.theCollisionBox.setOpaque(true);
		
		this.rectangleCollisionBox = this.theCollisionBox.getBounds();
		
		
		this.theImageIcon = new ImageIcon(imageFileName);
		this.theImage = this.theImageIcon.getImage();
		this.theImage = this.theImage.getScaledInstance(imagePlacement[2], imagePlacement[3], java.awt.Image.SCALE_SMOOTH);
		this.theImageIcon = new ImageIcon(this.theImage);
		
		this.theImageDisplay = new JLabel();
		this.theImageDisplay.setBounds(imagePlacement[0], imagePlacement[1], imagePlacement[2], imagePlacement[3]);
		this.theImageDisplay.setIcon(theImageIcon);
	}
	
	/**
	 * Gives JLabel of image to display in game
	 * @return JLabel of image to display
	 */
	
	public JLabel getImageDisplay() {
		return this.theImageDisplay;
	}
	
	/**
	 * Gives JLabel of collision box of obstacle for game to register
	 * @return JLabel of collision box
	 */
	
	public JLabel getCollisionBox() {
		return this.theCollisionBox;
	}
	
	/**
	 * Gives Rectangle of collision box of obstacle for game to register
	 * @return Rectangle of collision box
	 */
	
	public Rectangle getRectangleCollisonBox() {
		return this.rectangleCollisionBox;
	}
}
