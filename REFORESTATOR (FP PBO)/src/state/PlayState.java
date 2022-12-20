package state;

import java.awt.Graphics;

import entites.Handler;
import input.KeyHandler;
import util.ImageLoader;
import util.LoadSave;
import util.Image;
	// kelas yg digunakan untuk mengatur image yg akan digunakan pada map dan background nanti 
public class PlayState extends GameState {
	
	Level level;
	ImageLoader loader;
	Image textures;
	private int currentLevel = 1;
	private final int maxLevel = MAXLEVEL;
	private String mapLayoutPath;
	
	private int currLevel;
	//constructor
	public PlayState(KeyHandler keyHandler, GameStateManager gameStateManager, ImageLoader loader, Image textures) {
		this.keyHandler = keyHandler;
		this.gameStateManager = gameStateManager;
		this.loader = loader;
		this.textures = textures;
		this.currLevel = LoadSave.loadLevelProgress();
	}
	
	// UNTUK MENGATUR BACKGROUND
	public void levelInit() {
		setMapLayout();
		level = new Level(loader, keyHandler, new Handler(), gameStateManager, mapLayoutPath, textures, "/background/Refores_BG.png");
	}

	
	//UPDATE
	public void update() {
		System.out.println(currentLevel);
		level.update();

	}


	//DRAW
	public void draw(Graphics g) {
		level.draw(g);
	}
	// method yang akan digunakan ketika levele complete
	public void levelUp() {
		currentLevel++;
		if(currentLevel > currLevel) LoadSave.saveLevelProgress(currentLevel);
		if (currentLevel > maxLevel) System.exit(0); 
		levelInit();
	}

	private void setMapLayout() {
		switch(currentLevel) {
			case 1:
				this.mapLayoutPath = "/map/map1.png";
				break;
			case 2:
				this.mapLayoutPath = "/map/map2.png";
				break;
			case 3:
				this.mapLayoutPath = "/map/map3.png";
				break;
			case 4:
				this.mapLayoutPath = "/map/map4.png";
				break;
			case 5:
				this.mapLayoutPath = "/map/map5.png";
				break;
			case 6:
				this.mapLayoutPath = "/map/map6.png";
				break;
			case 7:
				this.mapLayoutPath = "/map/map7.png";
				break;
			case 8:
				this.mapLayoutPath = "/map/map8.png";
				break;
			
		}
	}
	
	public void setCurrentLevel(int level) {
		this.currentLevel = level;
	}
}
