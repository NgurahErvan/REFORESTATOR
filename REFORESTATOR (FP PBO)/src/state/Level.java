package state;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import entites.Enemy;
import entites.SuperEntityObject;
import input.KeyHandler;
import entites.Handler;
import entites.Player;
import main.GamePanel;
import tile.Bricks;
import tile.FinishPoint;
import tile.Heart;
import tile.Obstacle;
import tile.PathBlocker;
import util.Camera;
import util.ImageLoader;
import util.Image;
	//Kelas Untuk menentukan update di setiap level dan warna rgb yg digunakan masing" entites
public class Level {
	
	private ImageLoader loader;
	private BufferedImage levelLayout;
	private BufferedImage backGround;
	private KeyHandler keyHandler;
	private Handler handler;
	private GameStateManager gsm;
	private Image textures;
	private Camera camera = new Camera(0,0);
	// menentukan health player
	public static final int MAX_HEALTH = 5;
	private int playerHealth = MAX_HEALTH;
	// membuat arraylist untuk hearth nanti
	private ArrayList<Heart> playerLife;
	// Constructor
	public Level(ImageLoader loader, KeyHandler keyHandler, Handler handler, GameStateManager gsm,
			String levelLayoutPath, Image textures, String path) {
		this.loader = loader;
		this.keyHandler = keyHandler;
		this.handler = handler;
		this.textures = textures;
		this.gsm = gsm;
		
		this.backGround = loader.load(path);
		handlerInit(levelLayoutPath);
	}
	// fungsi untuk menggambarkan tampilan di map nanti
	public void handlerInit(String path) {
		this.levelLayout = loader.load(path);
		
		int w = levelLayout.getWidth();
		int h = levelLayout.getHeight();		
		//Menentukan Warna yg akan digunakan di map
		for(int x = 0; x < w; x++) {
			for(int y = 0; y < h; y++) {
				int pixel = levelLayout.getRGB(x, y);
				int red = (pixel >> 16) & 255;
				int green = (pixel >> 8) & 255;
				int blue = pixel & 255;

				//BLOCK
				//GREY
				if(red == 200 && green == 200 && blue == 200) {
					handler.add(new Bricks(GamePanel.TILESIZE*x, GamePanel.TILESIZE*y, textures.getBlockTextures()));
				}if(red == 230 && green == 230 && blue == 230) {
					handler.add(new Bricks(GamePanel.TILESIZE*x, GamePanel.TILESIZE*y, textures.getBlockTextures()));
				}if(red == 255 && green == 255 && blue == 255) {
					handler.add(new Bricks(GamePanel.TILESIZE*x, GamePanel.TILESIZE*y,  textures.getBlockTextures()));
				}if(red == 145 && green == 145 && blue == 145) {
					handler.add(new Bricks(GamePanel.TILESIZE*x, GamePanel.TILESIZE*y,  textures.getBlockTextures()));
				}if(red == 175 && green == 175 && blue == 175) {
					handler.add(new Bricks(GamePanel.TILESIZE*x, GamePanel.TILESIZE*y,  textures.getBlockTextures()));
				}if(red == 205 && green == 205 && blue == 205) {
					handler.add(new Bricks(GamePanel.TILESIZE*x, GamePanel.TILESIZE*y,  textures.getBlockTextures()));
				}if(red == 95 && green == 95 && blue == 95) {
					handler.add(new Bricks(GamePanel.TILESIZE*x, GamePanel.TILESIZE*y,  textures.getBlockTextures()));
				}if(red == 125 && green == 125 && blue == 125) {
					handler.add(new Bricks(GamePanel.TILESIZE*x, GamePanel.TILESIZE*y,  textures.getBlockTextures()));
				}if(red == 155 && green == 155 && blue == 155) {
					handler.add(new Bricks(GamePanel.TILESIZE*x, GamePanel.TILESIZE*y,  textures.getBlockTextures()));
				}
				
				if(red == 170 && green == 89 && blue == 89) {
					handler.add(new Bricks(GamePanel.TILESIZE*x, GamePanel.TILESIZE*y,  textures.getBlockTextures()));
				}if(red == 112 && green == 58 && blue == 58) {
					handler.add(new Bricks(GamePanel.TILESIZE*x, GamePanel.TILESIZE*y,  textures.getBlockTextures()));
				}if(red == 63 && green == 35 && blue == 35) {
					handler.add(new Bricks(GamePanel.TILESIZE*x, GamePanel.TILESIZE*y,  textures.getBlockTextures()));
				}
				if(red == 102 && green == 188 && blue == 121) {
					handler.add(new Bricks(GamePanel.TILESIZE*x, GamePanel.TILESIZE*y,  textures.getBlockTextures()));
				}if(red == 84 && green == 161 && blue == 101) {
					handler.add(new Bricks(GamePanel.TILESIZE*x, GamePanel.TILESIZE*y,  textures.getBlockTextures()));
				}if(red == 51 && green == 98 && blue == 62) {
					handler.add(new Bricks(GamePanel.TILESIZE*x, GamePanel.TILESIZE*y,  textures.getBlockTextures()));
				}
				
				if(red == 101 && green == 107 && blue == 36) {
					handler.add(new Bricks(GamePanel.TILESIZE*x, GamePanel.TILESIZE*y,  textures.getBlockTextures()));
				}
				
				//FLAG
				//Hijau
				if(red == 0 && green == 255 && blue == 0) {
					handler.add(new FinishPoint(GamePanel.TILESIZE*x, GamePanel.TILESIZE*y, textures.getFlagTextures()));
				}
				
				
				//VIRUS
				//Red
				if(red == 255 && green == 0 && blue == 0) {
					handler.add(new Enemy(GamePanel.TILESIZE * x, GamePanel.TILESIZE*y, textures.getVirusTextures(), handler));
				} 
				
				
				//PATHBLOCKER
				if(red == 0 && green == 235 && blue == 255) {
					handler.add(new PathBlocker(GamePanel.TILESIZE * x, GamePanel.TILESIZE * y));
				}
				
				
				//OBSTACLE
				//Yellow
				if(red == 235 && green == 255 && blue == 0) {
					handler.add(new Obstacle(GamePanel.TILESIZE*x, GamePanel.TILESIZE*y, textures.getObstacleTextures()));
				}
				
				
				//PLAYER
				//Blue
				if(red == 0 && green == 0 && blue == 255) {
					handler.add(new Player(GamePanel.TILESIZE*x, GamePanel.TILESIZE*y, keyHandler, handler, textures, gsm));
				}
				
				
				//PLAYER HEARTS
				playerLife = new ArrayList<Heart>();
				ArrayList<BufferedImage> heartImg = textures.getHeartTextures();
				for(int i = 0; i < MAX_HEALTH; i++) playerLife.add(new Heart(800 + (heartImg.get(0).getWidth() * GamePanel.SCALE + 10) * i, 10, heartImg, i+1));
			}
		}
	}
	
	
	//UPDATE
	public void update() {
		handler.update();
		for(SuperEntityObject x : handler.getEntities()) {
			if(x.getEntityID() == SuperEntityObject.PLAYER) {
				camera.update(x);
				playerHealth = x.getHealth();
			}
		}
	}
	
	
	//DRAW
	public void draw(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g.drawImage(backGround, 0, 0, GamePanel.WIDTH, GamePanel.HEIGHT, null);
		g2d.translate(camera.getX(), camera.getY());
		for (SuperEntityObject x : handler.getEntities()) {
			x.draw(g);
		}
		//Translates the origin of the Graphics2D context to thepoint (x, y) in the current coordinate system
		g2d.translate(-camera.getX(), -camera.getY());
	
		for(int i = 0; i < MAX_HEALTH; i++) {
			playerLife.get(i).draw(g, playerHealth);
		}
	}
}
