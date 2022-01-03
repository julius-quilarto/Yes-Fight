/**
 * Creates a window with a movable character
 * By Julius Quilarto
 * @version 10/02/21
 * Game Code Source: https://www.youtube.com/watch?v=IyfB0u9g2x0
 * Collision source: https://www.youtube.com/watch?v=Otl24e_nuyc
 * Don't go off screen: https://www.youtube.com/watch?v=Km81XyczqC4
 * Insert Images: https://www.java-examples.com/set-icon-jlabel-example and 
 * 		https://www.youtube.com/watch?v=eZrdU3BvI4E
 * Idea to set collectible background to null: https://bytes.com/topic/java/answers/640072-how-clear-jlabel
 * 
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.ArrayList;

public class Game2 {
	ArrayList<JLabel> obstacles = new ArrayList<JLabel>();
	ArrayList<Rectangle> obstacleRectangles = new ArrayList<Rectangle>();
	
	JFrame window;
	
	JLabel player;
	Rectangle playerRect;
	
	JLabel item;
	Rectangle itemRect;
	
	
	Action upAction;
	Action downAction;
	Action leftAction;
	Action rightAction;
	

	
	public Game2(){	
		ImageIcon rockIcon = new ImageIcon("rock.png");
		Image rockImage = rockIcon.getImage();
		Image modifiedRockImage = rockImage.getScaledInstance(80, 80, java.awt.Image.SCALE_SMOOTH);
		rockIcon = new ImageIcon(modifiedRockImage);
		
		ImageIcon guyIcon = new ImageIcon("guy.png");
		Image guyImage = guyIcon.getImage();
		Image modifiedGuyImage = guyImage.getScaledInstance(20, 30, java.awt.Image.SCALE_SMOOTH);
		guyIcon = new ImageIcon(modifiedGuyImage);
		
		
		JLabel obstacle = new JLabel();
		obstacle.setBounds(55,90,70,33);
		//obstacle.setIcon(rockIcon);
		//obstacle.setBackground(Color.BLACK);
		Rectangle obstacleRect = obstacle.getBounds();
		obstacle.setOpaque(true);
		obstacles.add(obstacle);
		obstacleRectangles.add(obstacleRect);
		
		JLabel image = new JLabel();
		image.setBounds(50, 50, 80, 80);
		image.setIcon(rockIcon);
		
	
		window = new JFrame("KeyBinding Demo");
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setSize(420,420);
		window.setLayout(null);
		
		
		player = new JLabel();
		player.setBounds(0, 0, 20, 30);
		player.setIcon(guyIcon);
		playerRect = player.getBounds();
		player.setOpaque(true);
		
		item = new JLabel();
		item.setBackground(Color.GREEN);
		item.setBounds(400, 400, 400, 400);
		itemRect = item.getBounds();
		item.setOpaque(true);
		
		upAction = new UpAction();
		downAction = new DownAction();
		leftAction = new LeftAction();
		rightAction = new RightAction();
		
		player.getInputMap().put(KeyStroke.getKeyStroke('w'), "upAction");
		player.getActionMap().put("upAction", upAction);
		player.getInputMap().put(KeyStroke.getKeyStroke('s'), "downAction");
		player.getActionMap().put("downAction", downAction);
		player.getInputMap().put(KeyStroke.getKeyStroke('a'), "leftAction");
		player.getActionMap().put("leftAction", leftAction);
		player.getInputMap().put(KeyStroke.getKeyStroke('d'), "rightAction");
		player.getActionMap().put("rightAction", rightAction);
		
		window.add(image);
		
		for(JLabel theObstacle : obstacles) {
			window.add(theObstacle);
		}
		
		window.add(player);
		
		window.add(item);
		

		
		
		window.setVisible(true);
	}
	
	public class UpAction extends AbstractAction{

		@Override
		public void actionPerformed(ActionEvent e) {
			if(player.getY() <= 0 ) {
				player.setLocation(player.getX(), player.getY());
				playerRect.setLocation(player.getX(), player.getY());
			}
			
			else if(isGoingToCollide(0,-10)) {
				player.setLocation(player.getX(), player.getY());
				playerRect.setLocation(player.getX(), player.getY());
			}
			
			
			else {
				player.setLocation(player.getX(), player.getY()-10);
				playerRect.setLocation(player.getX(), player.getY());
				
				
				if(playerRect.intersects(itemRect)) {
					System.out.println("Going to Collect");
					item.setBackground(null);
				}
				
				
				
			}
		}		
	}
	public class DownAction extends AbstractAction{

		@Override
		public void actionPerformed(ActionEvent e) {
			if(player.getY() >= 790) {
				player.setLocation(player.getX(), player.getY());
				playerRect.setLocation(player.getX(), player.getY());
			}
			
			else if(isGoingToCollide(0,10)) {
				player.setLocation(player.getX(), player.getY());
				playerRect.setLocation(player.getX(), player.getY());
			}
			
			
			else {
				player.setLocation(player.getX(), player.getY()+10);
				playerRect.setLocation(player.getX(), player.getY());
				
				
				if(playerRect.intersects(itemRect)) {
					System.out.println("Going to Collect");
					item.setBackground(null);
				}
				
			}	
		}		
	}
	public class LeftAction extends AbstractAction{

		@Override
		public void actionPerformed(ActionEvent e) {
			if(player.getX() <= 0) {
				player.setLocation(player.getX(), player.getY());
				playerRect.setLocation(player.getX(), player.getY());
			}
			
			else if(isGoingToCollide(-10,0)) {
				player.setLocation(player.getX(), player.getY());
				playerRect.setLocation(player.getX(), player.getY());
			}
			
			
			else {
				player.setLocation(player.getX()-10, player.getY());
				playerRect.setLocation(player.getX(), player.getY());
				
				if(playerRect.intersects(itemRect)) {
					System.out.println("Going to Collect");
					item.setBackground(null);
				}
				
			}
			
		}		
	}
	public class RightAction extends AbstractAction{

		@Override
		public void actionPerformed(ActionEvent e) {
			if(player.getX() >= 1520) {
				player.setLocation(player.getX(), player.getY());
				playerRect.setLocation(player.getX(), player.getY());
			}
			
			else if(isGoingToCollide(10, 0)) {
				player.setLocation(player.getX(), player.getY());
				playerRect.setLocation(player.getX(), player.getY());
			}
			
			
			else {
				player.setLocation(player.getX()+10, player.getY());
				playerRect.setLocation(player.getX(), player.getY());
				
				if(playerRect.intersects(itemRect)) {
					System.out.println("Going to Collect");
					item.setBackground(null);
				}
				
			}
		}		
	}
	
	private boolean isGoingToCollide(int xDirection, int yDirection) {	
		Rectangle potentialMove = playerRect;
		potentialMove.x = potentialMove.x + xDirection;
		potentialMove.y = potentialMove.y + yDirection;
		
		for(Rectangle theObstacleRectangle : obstacleRectangles) {
			if(potentialMove.intersects(theObstacleRectangle)) {
				//toReturn = true;
				return true;
			}
			else {
				continue;
			}			
		}
		
		return false;
	}
}
