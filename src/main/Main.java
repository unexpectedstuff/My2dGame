package main;

import java.awt.Graphics;

import javax.swing.JFrame;

public class Main {
	public static void main(String[] args) {
		JFrame window = new JFrame ();
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setResizable(false);
		window.setTitle("2D Adventure");
		
		GamePanel gamePanel = new GamePanel();
		window.add(gamePanel);
		
		window.pack(); //causes this window to be sized to fit the preferred size and layouts of its subcomponents (=GamePanel).
		
		window.setLocationRelativeTo(null); //center
		window.setVisible(true);
		
		gamePanel.startGameThread();
		
	}
}

