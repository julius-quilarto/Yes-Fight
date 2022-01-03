import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class MainMenu {
	public static void main(String[] args) {
		JFrame window = new JFrame("Yes!!! KILL!!!");
		
		JLabel welcomeback = new JLabel("Hello and welcome back to... Yes!!! KILL!!!");
		welcomeback.setVerticalAlignment(JLabel.TOP);
		welcomeback.setHorizontalAlignment(JLabel.CENTER);		 
		welcomeback.setBounds(300,10,800,200);
		welcomeback.setFont(new Font("Courier", Font.PLAIN,40));
		
		JButton startButton = new JButton("Begin");
		JButton contButton = new JButton("Continue");
		JButton optButton = new JButton("Options");
		JButton quitButton = new JButton("Quit");
		
		startButton.setBounds(600, 400, 200, 60);
		startButton.setHorizontalAlignment(JLabel.CENTER);
		contButton.setBounds(600, 460, 200, 60);
		contButton.setHorizontalAlignment(JLabel.CENTER);
		optButton.setBounds(600, 520, 200, 60);
		optButton.setHorizontalAlignment(JLabel.CENTER);
		quitButton.setBounds(600, 580, 200, 60);
		quitButton.setHorizontalAlignment(JLabel.CENTER);
		
		
		window.add(startButton);
		window.add(contButton);
		window.add(optButton);
		window.add(quitButton);
		window.add(welcomeback);

		startButton.addActionListener(new ActionListener(){  
			public void actionPerformed(ActionEvent e){  
	            Game game = new Game(0,0);
	            window.dispose();
	        }  
	    });  
		quitButton.addActionListener(new ActionListener(){  
			public void actionPerformed(ActionEvent e){  
	            System.exit(0);
	        }  
	    });  
		
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setExtendedState(JFrame.MAXIMIZED_BOTH);
		window.setLayout(null);
		window.setVisible(true);
	}
	
}

