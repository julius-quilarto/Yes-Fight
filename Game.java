/**
 * Creates a window with a movable character that explores 
 * By Julius Quilarto
 * @version 10/30/21
 * Game Code Source: https://www.youtube.com/watch?v=IyfB0u9g2x0
 * Collision source: https://www.youtube.com/watch?v=Otl24e_nuyc
 * Don't go off screen: https://www.youtube.com/watch?v=Km81XyczqC4
 * Insert Images: https://www.java-examples.com/set-icon-jlabel-example and 
 * 		https://www.youtube.com/watch?v=eZrdU3BvI4E
 * Idea to set collectible background to null: https://bytes.com/topic/java/answers/640072-how-clear-jlabel
 * Set Full Screen: https://stackoverflow.com/questions/11570356/jframe-in-full-screen-java
 * 
 */

import java.awt.*;
import java.util.Random;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

public class Game {
	Random rand = new Random();
	
	ArrayList<Obstacle> theObstacles = new ArrayList<Obstacle>();
	
	JFrame window;
	
	JLabel player;
	Rectangle playerRect;
	
	JLabel item1;
	Rectangle itemRect1;
	ArrayList<Rectangle> notCollectedRects = new ArrayList<Rectangle>();
	ArrayList<Rectangle> collectedRects = new ArrayList<Rectangle>();
	Queue<Rectangle> toRemove = new LinkedList<Rectangle>();
	
	
	Action upAction;
	Action downAction;
	Action leftAction;
	Action rightAction;
	Action pause;
	

	
	public Game(int startX, int startY){		
		this.makePlayer(startX, startY);
		
		int[] collisionBox = new int[4];
		collisionBox[0] = 55;
		collisionBox[1] = 90;
		collisionBox[2] = 70;
		collisionBox[3] = 33;
		
		int[] imagePlacement = new int[4];
		imagePlacement[0] = 50;
		imagePlacement[1] = 50;
		imagePlacement[2] = 80;
		imagePlacement[3] = 80;
		
		int[] collisionBox2 = new int[4];
		collisionBox2[0] = 205;
		collisionBox2[1] = 240;
		collisionBox2[2] = 70;
		collisionBox2[3] = 33;
		
		int[] imagePlacement2 = new int[4];
		imagePlacement2[0] = 200;
		imagePlacement2[1] = 200;
		imagePlacement2[2] = 80;
		imagePlacement2[3] = 80;
		
		
		String fileName = "rock.png";
		
		ImageIcon rockIcon = new ImageIcon("rock.png");
		Image rockImage = rockIcon.getImage();
		Image modifiedRockImage = rockImage.getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH);
		rockIcon = new ImageIcon(modifiedRockImage);
		
		item1 = new JLabel();
		item1.setBackground(Color.GREEN);
		//item1.setIcon(rockIcon);
		item1.setBounds(400, 400, 50, 50);
		itemRect1 = item1.getBounds();
		this.notCollectedRects.add(itemRect1);
		item1.setOpaque(true);
		
		Obstacle Obstacle1 = new Obstacle(collisionBox, imagePlacement, fileName);
		Obstacle Obstacle2 = new Obstacle(collisionBox2, imagePlacement2, fileName);
		this.theObstacles.add(Obstacle1);
		this.theObstacles.add(Obstacle2);
		
	
		window = new JFrame("Yes!!! KILL!!!");
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setExtendedState(JFrame.MAXIMIZED_BOTH);
		window.setLayout(null);
		
		

		
		
		
		this.drawObstacles(this.theObstacles);
		window.add(player);
		window.add(item1);
	
		window.setVisible(true);
	}
	
	public class UpAction extends AbstractAction{

		@Override
		public void actionPerformed(ActionEvent e) {
			if(isThereABatttle()) {
				//System.out.println("Having battle");
				window.dispose();
				BattleScreenDriver.main(player.getX(), player.getY());
			}
			
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
				
				if(!notCollectedRects.isEmpty()) {
					for(Rectangle aRect : notCollectedRects) {
						if(playerRect.intersects(aRect)) {
							System.out.println("Going to Collect");
							item1.setBackground(null);
							toRemove.add(aRect);
							collectedRects.add(aRect);
						}
					}					
				}
				
				if(!toRemove.isEmpty()) {
					while(!toRemove.isEmpty()) {
						notCollectedRects.remove(toRemove.remove());
					}
				}
				
				/*
				if(playerRect.intersects(itemRect)) {
					System.out.println("Going to Collect");
					item.setBackground(null);
				}
				*/
				
				
				
			}
		}		
	}
	public class DownAction extends AbstractAction{

		@Override
		public void actionPerformed(ActionEvent e) {
			if(isThereABatttle()) {
				//System.out.println("Having battle");
				window.dispose();
				BattleScreenDriver.main(player.getX(), player.getY());
			}
			
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
				
				
				if(!notCollectedRects.isEmpty()) {
					for(Rectangle aRect : notCollectedRects) {
						if(playerRect.intersects(aRect)) {
							System.out.println("Going to Collect");
							item1.setBackground(null);
							toRemove.add(aRect);
							collectedRects.add(aRect);
						}
					}					
				}
				
				if(!toRemove.isEmpty()) {
					while(!toRemove.isEmpty()) {
						notCollectedRects.remove(toRemove.remove());
					}
				}

				
				/*
				if(playerRect.intersects(itemRect)) {
					System.out.println("Going to Collect");
					item.setBackground(null);
				}
				*/
				
			}	
		}		
	}
	public class LeftAction extends AbstractAction{

		@Override
		public void actionPerformed(ActionEvent e) {
			if(isThereABatttle()) {
				//System.out.println("Having battle");
				window.dispose();
				BattleScreenDriver.main(player.getX(), player.getY());
			}
			
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
				
				if(!notCollectedRects.isEmpty()) {
					for(Rectangle aRect : notCollectedRects) {
						if(playerRect.intersects(aRect)) {
							System.out.println("Going to Collect");
							item1.setBackground(null);
							toRemove.add(aRect);
							collectedRects.add(aRect);
						}
					}					
				}
				
				if(!toRemove.isEmpty()) {
					while(!toRemove.isEmpty()) {
						notCollectedRects.remove(toRemove.remove());
					}
				}
				
				/*
				if(playerRect.intersects(itemRect)) {
					System.out.println("Going to Collect");
					item.setBackground(null);
				}
				*/
				
			}
			
		}		
	}
	public class RightAction extends AbstractAction{

		@Override
		public void actionPerformed(ActionEvent e) {
			if(isThereABatttle()) {
				//System.out.println("Having battle");
				window.dispose();
				BattleScreenDriver.main(player.getX(), player.getY());
			}
			
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
				
				if(!notCollectedRects.isEmpty()) {
					for(Rectangle aRect : notCollectedRects) {
						if(playerRect.intersects(aRect)) {
							System.out.println("Going to Collect");
							item1.setBackground(null);
							toRemove.add(aRect);
							collectedRects.add(aRect);
						}
					}					
				}
				
				if(!toRemove.isEmpty()) {
					while(!toRemove.isEmpty()) {
						notCollectedRects.remove(toRemove.remove());
					}
				}
				
				/*
				if(playerRect.intersects(itemRect)) {
					System.out.println("Going to Collect");
					item.setBackground(null);
				}
				*/
				
			}
		}		
	}
	
	public class pause extends AbstractAction {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			//System.out.println("Q");
			window.dispose();
			menu.main(player.getX(), player.getY());
		}
	}
	
	private boolean isGoingToCollide(int xDirection, int yDirection) {	
		Rectangle potentialMove = playerRect;
		potentialMove.x = potentialMove.x + xDirection;
		potentialMove.y = potentialMove.y + yDirection;
		
		for(Obstacle anObstacle : theObstacles) {
			if(potentialMove.intersects(anObstacle.getRectangleCollisonBox())) {
				//toReturn = true;
				return true;
			}
			else {
				continue;
			}			
		}
		
		return false;
	}
	
	private void drawObstacles(ArrayList<Obstacle> theObstacles) {
		for(Obstacle anObstacle : theObstacles) {
			window.add(anObstacle.getImageDisplay());
			window.add(anObstacle.getCollisionBox());
		}
	}
	
	private void makePlayer(int startX, int startY) {
		ImageIcon guyIcon = new ImageIcon("guy.png");
		Image guyImage = guyIcon.getImage();
		Image modifiedGuyImage = guyImage.getScaledInstance(20, 30, java.awt.Image.SCALE_SMOOTH);
		guyIcon = new ImageIcon(modifiedGuyImage);
		
		player = new JLabel();
		player.setBounds(startX, startY, 20, 30);
		player.setIcon(guyIcon);
		playerRect = player.getBounds();
		player.setOpaque(true);
		
		upAction = new UpAction();
		downAction = new DownAction();
		leftAction = new LeftAction();
		rightAction = new RightAction();
		pause = new pause();
		
		player.getInputMap().put(KeyStroke.getKeyStroke('w'), "upAction");
		player.getActionMap().put("upAction", upAction);
		
		player.getInputMap().put(KeyStroke.getKeyStroke('s'), "downAction");
		player.getActionMap().put("downAction", downAction);
		
		player.getInputMap().put(KeyStroke.getKeyStroke('a'), "leftAction");
		player.getActionMap().put("leftAction", leftAction);
		
		player.getInputMap().put(KeyStroke.getKeyStroke('d'), "rightAction");
		player.getActionMap().put("rightAction", rightAction);
		
		player.getInputMap().put(KeyStroke.getKeyStroke('q'), "pause");
		player.getActionMap().put("pause", pause);
		
	}
	
	private boolean isThereABatttle() {
		int randomNumber = rand.nextInt(100);
		if(randomNumber < 5)return true;
		return false;//default response
	}
}
