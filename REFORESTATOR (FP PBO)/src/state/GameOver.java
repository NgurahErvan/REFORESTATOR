package state;
// Kelas yang mengatur ketika kita tidak mencapai garis finish (game over)
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import input.KeyHandler;
import main.GamePanel;
import util.Animation;
import util.ImageLoader;
import util.Image;

public class GameOver extends GameState{
	private Image textures;
	private ArrayList<BufferedImage> backGroundImages;
	private Animation backGroundAnimation;
	
	private MenuButton button;
	private int currSelection = 0;
	
	public GameOver(KeyHandler keyHandler, GameStateManager gameStateManager, ImageLoader loader, Image textures) {
		this.keyHandler = keyHandler;
		this.gameStateManager = gameStateManager;
		this.textures = textures;
		//untuk membedakan idle dan active button
		BufferedImage idleButtonImage = this.textures.getButtonTextures().get(7);
		BufferedImage activeButtonImage = this.textures.getButtonTextures().get(8);
		// posisi dari button
		int x = GamePanel.WIDTH - activeButtonImage.getWidth() * GamePanel.SCALE + 20;
		int y = GamePanel.HEIGHT - activeButtonImage.getHeight() * GamePanel.SCALE;
		
		this.button = new MenuButton(x, y, idleButtonImage, activeButtonImage, 1, null, MenuButton.UNLOCKED, null);
		
		this.backGroundImages = this.textures.getLevelCompleteTextures();
		this.backGroundAnimation = new Animation(12, 6, 6, backGroundImages); //int frameDuration, int startIndex, int length, ArrayList<BufferedImage> animationSet)
	}
	
	
	//UPDATE
	public void update() {
		if(backGroundAnimation.getCurrFrameIndex() != 5) backGroundAnimation.runAnimation();
		
		else {						 // awalnya currselection adalah 0
			if(currSelection != 1) { // ketika sudah memencet AWSD maka button tidak akan bisa berpindah 
				if(keyHandler.getW()) { // karena currselectionya telah menjadi 1
					keyHandler.setW(false);
					currSelection = 1;
				}
				if(keyHandler.getA()) {
					keyHandler.setA(false);
					currSelection = 1;
				}
				if(keyHandler.getS()) {
					keyHandler.setS(false);
					currSelection = 1;
				}
				if(keyHandler.getD()) {
					keyHandler.setD(false);
					currSelection = 1;
				}
				if((keyHandler.getEnter())) {
					keyHandler.setEnter(false);
					
					currSelection = 1;
				}
			}else { // currselection = 1 enter bisa berfungsi
				if(( keyHandler.getEnter())) {
					keyHandler.setEnter(false);
					// ketika game over game langsung menuju ke arah menustate
					gameStateManager.setCurrentState(GameStateManager.MENUSTATE);
				}
			}
		}
	}

	
	//DRAW
	public void draw(Graphics g) {
		g.drawImage(backGroundAnimation.getCurrFrame(), 0, 0, GamePanel.WIDTH, GamePanel.HEIGHT, null);
		button.draw(g, currSelection);
	}

}
