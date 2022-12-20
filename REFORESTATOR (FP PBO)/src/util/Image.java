package util;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Image {
	private SpriteSheet blockSheet;
	private SpriteSheet playerSheet;
	private SpriteSheet enemySheet;
	private SpriteSheet flagSheet;
	
	private SpriteSheet menuSheet;
	private SpriteSheet levelCompleteSheet;
	
	private SpriteSheet buttonSheet;
	private SpriteSheet titlesSheet;
	private SpriteSheet numbersSheet;
	
	private SpriteSheet heartSheet;
	
	private ArrayList<BufferedImage> blockTextures = new ArrayList<BufferedImage>();  
	private ArrayList<BufferedImage> playerTextures = new ArrayList<BufferedImage>();
	private ArrayList<BufferedImage> obstacleTextures = new ArrayList<BufferedImage>();
	private ArrayList<BufferedImage> virusTextures = new ArrayList<BufferedImage>();
	private ArrayList<BufferedImage> flagTextures = new ArrayList<BufferedImage>();
	
	private ArrayList<BufferedImage> menuTextures = new ArrayList<BufferedImage>();
	private ArrayList<BufferedImage> levelCompleteTextures = new ArrayList<BufferedImage>();
	
	private ArrayList<BufferedImage> titleTextures = new ArrayList<BufferedImage>();
	private ArrayList<BufferedImage> buttonTextures = new ArrayList<BufferedImage>();
	private ArrayList<BufferedImage> numberTextures = new ArrayList<BufferedImage>();
	
	private ArrayList<BufferedImage> heartTextures = new ArrayList<BufferedImage>();
	
	ImageLoader loader;
	
	public Image(ImageLoader loader) {
		this.loader = loader;
		blockSheet = new SpriteSheet(loader, "/tiles/RefBlock.png");
		flagSheet = new SpriteSheet(loader, "/tiles/RefFinish.png");
		heartSheet = new SpriteSheet(loader, "/tiles/RefHeart.png");
		
		playerSheet = new SpriteSheet(loader, "/entities/RefPlayer.png");
		enemySheet = new SpriteSheet(loader, "/entities/RefEnemy.png");
		
		menuSheet = new SpriteSheet(loader, "/menu/RefMenu.png");
		levelCompleteSheet = new SpriteSheet(loader, "/menu/RefLevelComplete.png");
		buttonSheet = new SpriteSheet(loader, "/menu/RefButton.png");
		titlesSheet = new SpriteSheet(loader, "/menu/RefTitle.png");	
		numbersSheet = new SpriteSheet(loader, "/menu/RefNumber.png");
		
		
		init();
	}
	
	private void init() {
		//BLOCKS
		for(int i = 0; i < 3; i++) {
			for(int j = 0; j < 3; j++) {
				blockTextures.add(blockSheet.getSubImage(i, j, 16, 16));
			}
		}
		
		for(int i = 0; i < 3; i++) {
			blockTextures.add(blockSheet.getSubImage(i, 3, 16, 16));
		}for(int i = 0; i < 4; i++) {
			blockTextures.add(blockSheet.getSubImage(3, i, 16, 16));
		}

		//OBSTACLE
		for(int i = 0; i < 4; i++) {
			obstacleTextures.add(blockSheet.getSubImage(4, i, 16, 16));
		}
		
		//FLAG
		for(int i = 0; i < 6; i++) {
			flagTextures.add(flagSheet.getSubImage(0, i, 16, 32));
		}
		
		//PLAYER
		for(int i = 0; i < 8; i++) {
			playerTextures.add(playerSheet.getSubImage(0, i, 32, 32));
		}for(int i = 0; i < 8; i++) {
			playerTextures.add(playerSheet.getSubImage(1, i, 32, 32));
		}for(int i = 0; i < 8; i++) {
			playerTextures.add(playerSheet.getSubImage(2, i, 32, 32));
		}for(int i = 0; i < 8; i++) {
			playerTextures.add(playerSheet.getSubImage(3, i, 32, 32));
		}for(int i = 0; i < 8; i++) {
			playerTextures.add(playerSheet.getSubImage(4, i, 32, 32));
		}for(int i = 0; i < 8; i++) {
			playerTextures.add(playerSheet.getSubImage(5, i, 32, 32));
		}
		
		//VIRUS
		for(int i = 0; i < 8; i++) {
			virusTextures.add(enemySheet.getSubImage(0, i, 32, 32));
		}for(int i = 0; i < 8; i++) {
			virusTextures.add(enemySheet.getSubImage(1, i, 32, 32));
		}for(int i = 0; i < 8; i++) {
			virusTextures.add(enemySheet.getSubImage(2, i, 32, 32));
		}
		
		//MENU
		for(int i = 0; i < 2; i++) {
			menuTextures.add(menuSheet.getSubImage(0, i, 320, 180));
			menuTextures.add(menuSheet.getSubImage(1, i, 320, 180));
			menuTextures.add(menuSheet.getSubImage(2, i, 320, 180));
			menuTextures.add(menuSheet.getSubImage(3, i, 320, 180));
		}
		
		//LEVEL COMPLETE
		for(int i = 0; i < 4; i++) {
			levelCompleteTextures.add(levelCompleteSheet.getSubImage(i, 0, 640, 360));
			levelCompleteTextures.add(levelCompleteSheet.getSubImage(i, 1, 640, 360));
			levelCompleteTextures.add(levelCompleteSheet.getSubImage(i, 2, 640, 360));
		}
		
		//Button
		for(int i = 0; i < 4; i++) {
			buttonTextures.add(buttonSheet.getSubImage(i, 0, 44, 17));
		}
		for(int i = 0; i < 2; i++) {
			buttonTextures.add(buttonSheet.getSubImage(i, 0, 74, 30, 0, 4*17));
		}buttonTextures.add(buttonSheet.getSubImage(0, 0, 74, 30, 44, 0));
		for(int i = 0; i < 2; i++) {
			buttonTextures.add(buttonSheet.getSubImage(i, 0, 117, 79, 0, 128));
		}
		
		//TITLE
		titleTextures.add(titlesSheet.getSubImage(0, 0, 192, 40));
		titleTextures.add(titlesSheet.getSubImage(0, 0, 80, 23, 0, 40));
		
		//NUMBERS
		for(int i = 0; i < 2; i++) {
			for(int j = 0; j < 5; j++) {
				numberTextures.add(numbersSheet.getSubImage(i, j, 9, 14));
			}
		}
		
		//HEARTS
		for(int i = 0; i < 2; i++) {
			heartTextures.add(heartSheet.getSubImage(0, i, 16, 16));
		}
	}
	
	public ArrayList<BufferedImage> getBlockTextures() {return blockTextures;}
	public ArrayList<BufferedImage> getObstacleTextures() {return obstacleTextures;}
	public ArrayList<BufferedImage> getFlagTextures() {return flagTextures;}
	
	public ArrayList<BufferedImage> getPlayerTextures() {return playerTextures;}
	public ArrayList<BufferedImage> getVirusTextures() {return virusTextures;}
	
	public ArrayList<BufferedImage> getButtonTextures() {return buttonTextures;}
	public ArrayList<BufferedImage> getMenuTextures() {return menuTextures;}
	public ArrayList<BufferedImage> getLevelCompleteTextures() {return levelCompleteTextures;}
	
	public ArrayList<BufferedImage> getTitleTextures() {return titleTextures;}
	public ArrayList<BufferedImage> getNumberTextures() {return numberTextures;}
	
	public ArrayList<BufferedImage> getHeartTextures() {return heartTextures;}
}
