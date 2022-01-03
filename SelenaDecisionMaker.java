import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
public class SelenaDecisionMaker {

	public static void main(int[] taniaDecision, int[] dargoniDecision, int[] selenaDecision, int[] yourNameDecision, BattleManager bm,
			int xPos, int yPos) {
		// TODO Auto-generated method stub
		System.out.println("Tania Decision Array: " + taniaDecision[0] + taniaDecision[1] + taniaDecision[2]);
		System.out.println("Dargoni Decision Array: " + dargoniDecision[0] + dargoniDecision[1] + dargoniDecision[2]);
		//int[] selenaDecision = {1, 1, 1};
		
		JFrame window = new JFrame("Yes!!! KILL!!!");
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setExtendedState(JFrame.MAXIMIZED_BOTH);
		
		JLabel selenaPicture = new JLabel();
		selenaPicture.setBounds(1030, 25, 200, 200);
		//picture.setBackground(Color.GREEN);
		//picture.setOpaque(true);
		
		ImageIcon thePictureIcon = new ImageIcon("Magical_Girl.gif");
		Image thePicture = thePictureIcon.getImage();
		thePicture = thePicture.getScaledInstance(200, 200, java.awt.Image.SCALE_SMOOTH);
		thePictureIcon = new ImageIcon(thePicture);
		
		selenaPicture.setIcon(thePictureIcon);
		
		JLabel message = new JLabel("What will Selena do?");
		message.setVerticalAlignment(JLabel.CENTER);
		message.setHorizontalAlignment(JLabel.CENTER); 
		message.setBounds(480,10,550,45);
		message.setFont(new Font("Courier", Font.PLAIN,40));
		
		JButton attackButton1 = new JButton("Ethereal Blade");
		JButton attackButton2 = new JButton("Moon Kick");
		JButton attackButton3 = new JButton("Mega Buster");
		JButton attackButton4 = new JButton("Shocking Fist");
		attackButton1.setBounds(100, 250, 200, 90);
		attackButton2.setBounds(290, 250, 200, 90);
		attackButton3.setBounds(100, 330, 200, 90);
		attackButton4.setBounds(290, 330, 200, 90);
		
		JButton spellButton1 = new JButton("Heal Spell");
		JButton spellButton2 = new JButton("Team Heal");
		JButton spellButton3 = new JButton("Tidal Wave");
		JButton spellButton4 = new JButton("Fairy Glitter");
		spellButton1.setBounds(510, 250, 200, 90);
		spellButton2.setBounds(710, 250, 200, 90);
		spellButton3.setBounds(510, 330, 200, 90);
		spellButton4.setBounds(710, 330, 200, 90);
		
		JButton prizesButton = new JButton("Prizes");
		JButton defendButton = new JButton("Defend");
		JButton tauntButton = new JButton("Taunt");
		JButton runButton = new JButton("Run");
		prizesButton.setBounds(930, 250, 200, 90);
		defendButton.setBounds(1130, 250, 200, 90);
		tauntButton.setBounds(930, 330, 200, 90);
		runButton.setBounds(1130, 330, 200, 90);
		
		JButton ultimateMoveButton = new JButton("The Real Prize Was Friendship");
		ultimateMoveButton.setBounds(800, 440, 220, 90);
		
		JButton target1Button = new JButton("1");
		target1Button.setBounds(100, 700, 200, 90);
		JButton target2Button = new JButton("2");
		target2Button.setBounds(300, 700, 200, 90);
		JButton target3Button = new JButton("3");
		target3Button.setBounds(500, 700, 200, 90);
		JButton target4Button = new JButton("4");
		target4Button.setBounds(700, 700, 200, 90);
		
		JButton confirmButton = new JButton("Confirm");
		confirmButton.setBounds(1130, 700, 200, 90);
		
		JLabel targetMessage = new JLabel("Select a target:");
		targetMessage.setBounds(100,650,550,45);
		targetMessage.setFont(new Font("Courier", Font.PLAIN,40));
		
		JLabel ultAttackMessage = new JLabel("Ultimate Attack:");
		ultAttackMessage.setBounds(350, 440, 550, 90);
		ultAttackMessage.setFont(new Font("Courier", Font.PLAIN,40));
		
		attackButton1.addActionListener(new ActionListener(){  
			public void actionPerformed(ActionEvent e){  
	            selenaDecision[0] = 1;
	            selenaDecision[1] = 1;
	            System.out.println("Array Change: " + selenaDecision[0] + selenaDecision[1] + selenaDecision[2]);
	        }  
	    });
		
		attackButton2.addActionListener(new ActionListener(){  
			public void actionPerformed(ActionEvent e){  
	            selenaDecision[0] = 1;
	            selenaDecision[1] = 2;
	            System.out.println("Array Change: " + selenaDecision[0] + selenaDecision[1] + selenaDecision[2]);
	        }  
	    });
		
		attackButton3.addActionListener(new ActionListener(){  
			public void actionPerformed(ActionEvent e){  
	            selenaDecision[0] = 1;
	            selenaDecision[1] = 3;
	            System.out.println("Array Change: " + selenaDecision[0] + selenaDecision[1] + selenaDecision[2]);
	        }  
	    });
		
		attackButton4.addActionListener(new ActionListener(){  
			public void actionPerformed(ActionEvent e){  
	            selenaDecision[0] = 1;
	            selenaDecision[1] = 4;
	            System.out.println("Array Change: " + selenaDecision[0] + selenaDecision[1] + selenaDecision[2]);
	        }  
	    });
		
		spellButton1.addActionListener(new ActionListener(){  
			public void actionPerformed(ActionEvent e){  
	            selenaDecision[0] = 2;
	            selenaDecision[1] = 1;
	            System.out.println("Array Change: " + selenaDecision[0] + selenaDecision[1] + selenaDecision[2]);
	        }  
	    });
		
		spellButton2.addActionListener(new ActionListener(){  
			public void actionPerformed(ActionEvent e){  
	            selenaDecision[0] = 2;
	            selenaDecision[1] = 2;
	            System.out.println("Array Change: " + selenaDecision[0] + selenaDecision[1] + selenaDecision[2]);
	        }  
	    });
		
		spellButton3.addActionListener(new ActionListener(){  
			public void actionPerformed(ActionEvent e){  
	            selenaDecision[0] = 2;
	            selenaDecision[1] = 3;
	            System.out.println("Array Change: " + selenaDecision[0] + selenaDecision[1] + selenaDecision[2]);
	        }  
	    });
		
		spellButton4.addActionListener(new ActionListener(){  
			public void actionPerformed(ActionEvent e){  
	            selenaDecision[0] = 2;
	            selenaDecision[1] = 4;
	            System.out.println("Array Change: " + selenaDecision[0] + selenaDecision[1] + selenaDecision[2]);
	        }  
	    });
		
		prizesButton.addActionListener(new ActionListener(){  
			public void actionPerformed(ActionEvent e){  
	            selenaDecision[0] = 3;
	            selenaDecision[1] = 1;
	            System.out.println("Array Change: " + selenaDecision[0] + selenaDecision[1] + selenaDecision[2]);
	        }  
	    });
		
		defendButton.addActionListener(new ActionListener(){  
			public void actionPerformed(ActionEvent e){  
	            selenaDecision[0] = 4;
	            selenaDecision[1] = 1;
	            System.out.println("Array Change: " + selenaDecision[0] + selenaDecision[1] + selenaDecision[2]);
	        }  
	    });
		
		tauntButton.addActionListener(new ActionListener(){  
			public void actionPerformed(ActionEvent e){  
	            selenaDecision[0] = 5;
	            selenaDecision[1] = 1;
	            System.out.println("Array Change: " + selenaDecision[0] + selenaDecision[1] + selenaDecision[2]);
	        }  
	    });
		
		runButton.addActionListener(new ActionListener(){  
			public void actionPerformed(ActionEvent e){  
	            selenaDecision[0] = 6;
	            selenaDecision[1] = 1;
	            System.out.println("Array Change: " + selenaDecision[0] + selenaDecision[1] + selenaDecision[2]);
	            window.dispose();
	            Game game = new Game(xPos, yPos);
	        }  
	    });
		
		ultimateMoveButton.addActionListener(new ActionListener(){  
			public void actionPerformed(ActionEvent e){  
	            selenaDecision[0] = 1;
	            selenaDecision[1] = 5;
	            System.out.println("Array Change: " + selenaDecision[0] + selenaDecision[1] + selenaDecision[2]);
	        }  
	    });
		
		target1Button.addActionListener(new ActionListener(){  
			public void actionPerformed(ActionEvent e){  
	            selenaDecision[2] = 1;
	            System.out.println("Array Change: " + selenaDecision[0] + selenaDecision[1] + selenaDecision[2]);
	        }  
	    });
		
		target2Button.addActionListener(new ActionListener(){  
			public void actionPerformed(ActionEvent e){  
	            selenaDecision[2] = 2;
	            System.out.println("Array Change: " + selenaDecision[0] + selenaDecision[1] + selenaDecision[2]);
	        }  
	    });
		
		target3Button.addActionListener(new ActionListener(){  
			public void actionPerformed(ActionEvent e){  
	            selenaDecision[2] = 3;
	            System.out.println("Array Change: " + selenaDecision[0] + selenaDecision[1] + selenaDecision[2]);
	        }  
	    });
		
		target4Button.addActionListener(new ActionListener(){  
			public void actionPerformed(ActionEvent e){  
	            selenaDecision[2] = 4;
	            System.out.println("Array Change: " + selenaDecision[0] + selenaDecision[1] + selenaDecision[2]);
	        }  
	    });
		
		confirmButton.addActionListener(new ActionListener(){  
			public void actionPerformed(ActionEvent e){  
	            System.out.println("Selena array: " + selenaDecision[0] + selenaDecision[1] + selenaDecision[2]);
	            YourNameDecisionMaker.main(taniaDecision, dargoniDecision, selenaDecision, yourNameDecision, bm, xPos, yPos);
	            window.dispose();
	        }  
	    });
		
		window.add(message);
		window.add(targetMessage);
		window.add(ultAttackMessage);
		
		window.add(attackButton1);
		window.add(attackButton2);
		window.add(attackButton3);
		window.add(attackButton4);
		
		window.add(spellButton1);
		window.add(spellButton2);
		window.add(spellButton3);
		window.add(spellButton4);
		
		window.add(prizesButton);
		window.add(defendButton);
		window.add(tauntButton);
		window.add(runButton);
		
		window.add(ultimateMoveButton);
		
		window.add(target1Button);
		window.add(target2Button);
		window.add(target3Button);
		window.add(target4Button);
		
		window.add(confirmButton);
		
		window.add(selenaPicture);
		
		window.setLayout(null);
		window.setVisible(true);
	}

}
