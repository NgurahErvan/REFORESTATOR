package main;

import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JPanel;

import input.KeyHandler;
import state.GameStateManager;
import util.LoadSave;


public class GamePanel extends JPanel implements Runnable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public final static int WIDTH = 1000; 
	public final static int	HEIGHT = 500 ; 
	public final static int TRUTILESIZE = 16; 
	public final static int SCALE = 2;
	public final static int FPS = 60;
	public final static int TILESIZE = TRUTILESIZE * SCALE;
	
	private KeyHandler keyHandler = new KeyHandler();
	Thread gameThread;
	private GameStateManager gameStateManager = new GameStateManager(keyHandler);

	
	public GamePanel() {
		this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		this.setFocusable(true);
		this.addKeyListener(keyHandler);
		
		LoadSave.createFile();
	}
	
	public void start() {
		gameThread = new Thread(this);
		gameThread.start();
	}
	
	@Override
	public void run() {
		
		double drawInterval = 1000000000/FPS;
		double delta = 0;
		long lastTime = System.nanoTime();
		long currentTime;
		
		while(gameThread != null) {
			
			currentTime = System.nanoTime();
			
			delta += (currentTime - lastTime) /drawInterval;
			
			lastTime = currentTime;
			
			if (delta >= 1){
				update();
				repaint();
				delta-- ;
			}
		}
	}
	
	//UPDATE
	public void update() {gameStateManager.update();}
	
	
	//DRAW
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		gameStateManager.draw(g);
		
		g.dispose();
	}
	
	public static void main(String[] args) {
		new Main(new GamePanel());
	}
	
}
