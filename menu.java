

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class menu {
	public static void main(int xPos, int yPos) {
		JFrame window = new JFrame("Yes!!! KILL!!!");
		
		JLabel message = new JLabel("Main Menu");
		message.setVerticalAlignment(JLabel.CENTER);
		message.setHorizontalAlignment(JLabel.CENTER); 
		message.setBounds(400,10,900,45);
		message.setFont(new Font("Courier", Font.PLAIN,40));
		
		JButton charsButton = new JButton("Contestants");
		JButton itemsButton = new JButton("Prizes");
		JButton equipButton = new JButton("Equip");
		JButton helpButton = new JButton("Help");
		JButton saveButton = new JButton("Save");
		JButton quitButton = new JButton("Quit");
		JButton backButton = new JButton("Back");
		
		charsButton.setBounds(0, 0, 200, 90);
		itemsButton.setBounds(0, 90, 200, 90);
		equipButton.setBounds(0, 180, 200, 90);
		helpButton.setBounds(0, 270, 200, 90);
		saveButton.setBounds(0, 360, 200, 90);
		quitButton.setBounds(0, 450, 200, 90);
		backButton.setBounds(0, 540, 200, 90);
		
		window.add(charsButton);
		window.add(itemsButton);
		window.add(equipButton);
		window.add(helpButton);
		window.add(saveButton);
		window.add(quitButton);
		window.add(backButton);
		window.add(message);
		
		charsButton.addMouseListener(new java.awt.event.MouseAdapter() {
		    public void mouseEntered(java.awt.event.MouseEvent evt) {
		    	message.setText("Let's see how our contestants are doing!"); 
		    }

		    public void mouseExited(java.awt.event.MouseEvent evt) {
		    	message.setText("Main Menu"); 
		    }
		});
		itemsButton.addMouseListener(new java.awt.event.MouseAdapter() {
		    public void mouseEntered(java.awt.event.MouseEvent evt) {
		    	message.setText("What fabulous prizes have you won so far?"); 
		    }

		    public void mouseExited(java.awt.event.MouseEvent evt) {
		    	message.setText("Main Menu"); 
		    }
		});
		equipButton.addMouseListener(new java.awt.event.MouseAdapter() {
		    public void mouseEntered(java.awt.event.MouseEvent evt) {
		    	message.setText("Are you planning to use a prize?"); 
		    }

		    public void mouseExited(java.awt.event.MouseEvent evt) {
		    	message.setText("Main Menu"); 
		    }
		});
		helpButton.addMouseListener(new java.awt.event.MouseAdapter() {
		    public void mouseEntered(java.awt.event.MouseEvent evt) {
		    	message.setText("Let's hear the rules again!"); 
		    }

		    public void mouseExited(java.awt.event.MouseEvent evt) {
		    	message.setText("Main Menu"); 
		    }
		});
		saveButton.addMouseListener(new java.awt.event.MouseAdapter() {
		    public void mouseEntered(java.awt.event.MouseEvent evt) {
		    	message.setText("Let's go to a quick commercial break!"); 
		    }

		    public void mouseExited(java.awt.event.MouseEvent evt) {
		    	message.setText("Main Menu"); 
		    }
		});
		quitButton.addMouseListener(new java.awt.event.MouseAdapter() {
		    public void mouseEntered(java.awt.event.MouseEvent evt) {
		    	message.setText("Come back tomorrow for the thrilling continuation!"); 
		    }

		    public void mouseExited(java.awt.event.MouseEvent evt) {
		    	message.setText("Main Menu"); 
		    }
		});
		backButton.addMouseListener(new java.awt.event.MouseAdapter() {
		    public void mouseEntered(java.awt.event.MouseEvent evt) {
		    	message.setText("Let's get back to the action!"); 
		    }

		    public void mouseExited(java.awt.event.MouseEvent evt) {
		    	message.setText("Main Menu"); 
		    }
		});
		
		backButton.addActionListener(new ActionListener(){  
			public void actionPerformed(ActionEvent e){  
	            //System.exit(0);
				window.dispose();
	            Game game = new Game(xPos, yPos);
	        }  
	    });  
		
		quitButton.addActionListener(new ActionListener(){  
			public void actionPerformed(ActionEvent e){  
	            //System.exit(0);
				window.dispose();
	            //Game game = new Game(xPos, yPos);
	        }  
	    });  
		
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setExtendedState(JFrame.MAXIMIZED_BOTH);
		window.setLayout(null);
		window.setVisible(true);
	}
	
}
