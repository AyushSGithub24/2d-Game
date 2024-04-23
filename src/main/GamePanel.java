package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import entity.Player;
import tile.TileManager;

public class GamePanel extends JPanel implements Runnable{
	//Screen Setting
	public final int originalTileSize=16;// 16x16 size
	public final int scale =3;
	public final int tileSize=originalTileSize*scale;
	public final int maxScreenCol=16;
	public final int maxScreenRow=12;
	public final int screenWidth=tileSize*maxScreenCol;//768 pixels
	public final int screenHeight=tileSize*maxScreenRow;// 576 pixels
	
	KeyHandler keyH=new KeyHandler();
	Thread gameThread;
	TileManager tileM=new TileManager(this);
	Player player=new Player(this,keyH);
	
	// Set player's default position
	
//	int playerX=100;
//	int playerY=100;
//	int playerSpeed=5;
	int FPS=60;
	long timer=0;
	long drawCount=0;
	
	public GamePanel() {
		
		this.setPreferredSize(new Dimension(screenWidth,screenHeight));
		this.setBackground(Color.BLACK);
		this.setDoubleBuffered(true);
		this.addKeyListener(keyH);
		this.setFocusable(true);
		
	}
	
	public void startGameThread() {
		gameThread=new Thread(this);
		gameThread.start();
	}

	@Override
/*	public void run() { Working gameloop using sleep method
		// TODO Auto-generated method stub
		double drawInterval=1000_000_000/FPS;
		double nextDrawTime=System.nanoTime()+drawInterval;
		
		while(gameThread!=null) {
//			System.out.println("the game loop is running");
//			long currentTime=System.nanoTime(); TO SHOW SYSTEM TIME IN NANO SECONDS TO SHOW ACCURACY 
			
			
			//1 UDPATE: update information such as character positions
			update();
			// 2 Draw: draw the screen with the updated information
			repaint();
			
			try {
				double remainingTime=nextDrawTime-System.nanoTime();
				remainingTime/=1000000;
				if(remainingTime<0) remainingTime=0;
				Thread.sleep((long) remainingTime);
				nextDrawTime+= drawInterval;
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
	*/
	public void run() {//Using Dela method for gameloop 
		double drawInterval=1000000000/FPS;
		double delta=0;
		long lastTime=System.nanoTime();
		long currentTime;
		
		while(gameThread!=null) {
			currentTime=System.nanoTime();
			
			delta+=(currentTime-lastTime)/drawInterval;
			timer+=(currentTime-lastTime);
			lastTime=currentTime;
			
			if(delta>=1) {
			update();
			repaint();
				delta--;
				drawCount++;
			}
			
//			if(timer>=1000000000) {
//				System.out.println("FPS:"+drawCount);
//				drawCount=0;
//				timer=0;
//			}
		}
	}
	public void update() {
		player.update();
	}
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		Graphics2D g2=(Graphics2D)g;
		tileM.draw(g2);
		player.draw(g2);
		
		g2.dispose();
	}
	
	
	
	
}
