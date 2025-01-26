package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

public class GamePanel extends JPanel implements Runnable{
	
	// SCREEN SETTINGS
	final int originalTileSize = 16; // 16x16 tile
	final int scale = 3; //integer scaling of graphics
	
	final int tileSize = originalTileSize * scale; // 48x48 tile 
	final int maxScreenCol = 16;
	final int maxScreenRow = 12; 
	final int screenWidth = tileSize * maxScreenCol; // 768 pixels
	final int screenHeight = tileSize * maxScreenRow; // 576 pixels
	
	KeyHandler keyH = new KeyHandler ();
	
	Thread gameThread;
	
	//  Set player's default position
	int playerX = 100;
	int playerY = 100;
	int playerSpeed = 4;
	
	
	public GamePanel () {
		
		this.setPreferredSize(new Dimension(screenWidth, screenHeight));
		this.setBackground(Color.black);
		this.setDoubleBuffered(true);
		this.addKeyListener(keyH);
		this.setFocusable(true);  //with this, this GamePabel can be "focused" to receive key input
 		
	}
	
	public void startGameThread() {
		gameThread = new Thread (this);
		gameThread.start();
	}
	
	@Override
	public void run() {
		//gameLoop
		while (gameThread != null) {
//			System.out.println("The game loop is running");
			
			// 1.  UPDATE: update information such as character positions
			update();
			// 2. DRAW: draw the screen with updated information
			repaint(); // that's how paintComponent called.
		}
	}
		
	public void update() {
		
	}
	public void paintComponent(Graphics g) { 
		
		super.paintComponent (g); //Super means - component of JPanel. GamePanel is subclass of Jpanel
	
		Graphics2D g2 = (Graphics2D)g; //This means that we changed this Graphics G to this Graphics2d class. extends Graphics class to provide more sophisticated control over geometry, coordinate transformations, color managment, and text layot.
	
		g2.setColor(Color.white);
		
		g2.fillRect(playerX, playerY, tileSize, tileSize);
		
		g2.dispose (); //dispose of this graphics context and release any system resources that it is using. To save memory basically
	
	
	}	
}
		

