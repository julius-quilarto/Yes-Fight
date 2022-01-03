/**
 * Creates a screen to visualize the battle
 * @author Julius Quilarto
 * @version 10/30/2021
 *
 */

import java.awt.*;
import java.awt.geom.*;
import javax.swing.*;

public class BattleScreen {
	JFrame window;
	JLabel player1;
	JLabel player2;
	JLabel player3;
	JLabel player4;
	
	public BattleScreen() {
		window = new JFrame("Yes!!! KILL!!!");
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setExtendedState(JFrame.MAXIMIZED_BOTH);
		window.setLayout(null);
		
		player1 = new JLabel();
		player1.setBounds(1250, 100, 60, 80);
		player1.setBackground(Color.BLUE);
		player1.setOpaque(true);
		
		player2 = new JLabel();
		player2.setBounds(1180, 200, 60, 80);
		player2.setBackground(Color.BLUE);
		player2.setOpaque(true);
		
		player3 = new JLabel();
		player3.setBounds(1250, 300, 60, 80);
		player3.setBackground(Color.BLUE);
		player3.setOpaque(true);
		
		player4 = new JLabel();
		player4.setBounds(1180, 400, 60, 80);
		player4.setBackground(Color.BLUE);
		player4.setOpaque(true);
		
		
		
		
		window.add(player1);
		window.add(player2);
		window.add(player3);
		window.add(player4);
		
		window.setVisible(true);
	}
}
