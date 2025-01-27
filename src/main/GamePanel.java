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
	
	//FPS
	int FPS = 60;
	
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
		
		double drawInterval = 1000000000/FPS; // 0.01666 seconds. This is a sleep method of restricting gameloop time
		double nextDrawTime = System.nanoTime() + drawInterval; // sleep method
		
		//gameLoop
		while (gameThread != null) {
//			System.out.println("The game loop is running");
			
//			long currentTime = System.nanoTime(); 
//			System.out.println("current Time: "+currentTime); how time works
			
			// 1.  UPDATE: update information such as character positions
			update();
			// 2. DRAW: draw the screen with updated information
			repaint(); // that's how paintComponent called.
			
			//sleep method
			try {
				double remainingTime = nextDrawTime - System.nanoTime(); 
				remainingTime = remainingTime/1000000; //convert from nanoseconds to milliseconds 
				
				if(remainingTime < 0) {
					remainingTime = 0;
				}
				
				Thread.sleep((long) remainingTime);
				
				nextDrawTime += drawInterval;
				
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			//end of sleep method
		}
	}
		
	public void update() {
		 if(keyH.upPressed == true) {
			 playerY -= playerSpeed; //playerY = playerY - playerSpeed;	 
		 }
		 else if(keyH.downPressed == true) {
			 playerY += playerSpeed;  
		 }
		 else if(keyH.leftPressed == true) {
			 playerX -= playerSpeed;  
		 }
		 else if(keyH.rightPressed == true) {
			 playerX += playerSpeed;  
		 }
	}
	public void paintComponent(Graphics g) { 
		
		super.paintComponent (g); //Super means - component of JPanel. GamePanel is subclass of Jpanel
	
		Graphics2D g2 = (Graphics2D)g; //This means that we changed this Graphics G to this Graphics2d class. extends Graphics class to provide more sophisticated control over geometry, coordinate transformations, color managment, and text layot.
	
		g2.setColor(Color.white);
		
		g2.fillRect(playerX, playerY, tileSize, tileSize);
		
		g2.dispose (); //dispose of this graphics context and release any system resources that it is using. To save memory basically
	
	
	}	
}
		

