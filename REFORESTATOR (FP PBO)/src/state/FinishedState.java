package state;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import input.KeyHandler;
import main.GamePanel;
import util.Animation;
import util.Image;
	// kelas untuk mengatur tampilan ketika berhasil mencapai finish point
public class FinishedState extends GameState{
	
	private ArrayList<BufferedImage> FinishImages;
	private Animation FinishAnimation;
	private int timer;
	
	public FinishedState(KeyHandler keyHandler, GameStateManager gsm, Image textures) {
		this.keyHandler = keyHandler;
		this.gameStateManager = gsm;
		this.textures = textures;
		
		this.FinishImages = textures.getLevelCompleteTextures();
		this.FinishAnimation = new Animation(15, 0, 6, this.FinishImages); //int frameDuration, int startIndex, int length, ArrayList<BufferedImage> animationSet)
	}
	
	
	//UPDATE
	public void update() {
		if(FinishAnimation.getCurrFrameIndex() == 5) {
			if(timer == 1) {
				timer = 0;
				FinishAnimation.setCurrFrameIndex(0);
				//akan otomatis menaikan level
				gameStateManager.getPlayState().levelUp();
				gameStateManager.setCurrentState(GameStateManager.PLAYSTATE);
			}
			animationTimer();
		}else FinishAnimation.runAnimation();
	}

	
	//DRAW
	public void draw(Graphics g) {
		g.drawImage(FinishAnimation.getCurrFrame(),0,0,GamePanel.WIDTH,GamePanel.HEIGHT,null);//Image img, int x, int y, int width, int height, ImageObserver observer)
	}
	
	
	private void animationTimer() {
		if(timer == 0) timer = 50;
		else timer--;
	}
}
