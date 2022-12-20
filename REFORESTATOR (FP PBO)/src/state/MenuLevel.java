package state;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import input.KeyHandler;
import main.GamePanel;
import util.LoadSave;
import util.Image;
// Kelas yang mengatur tampilan di menu ketika ingin memilih level
public class MenuLevel extends GameState{
	private int currSelection;
	private int maxLevel = MAXLEVEL;
	private int currLevel;
	private int rows = 4, cols = 4;
	private int distance = 40;
	
	private KeyHandler keyHandler;
	private ArrayList<MenuButton> buttons;
	private GameStateManager gameStateManager;

	private Image textures;
	private BufferedImage titleImage;
	
	public MenuLevel(KeyHandler keyHandler, GameStateManager gameStateManager, Image textures) {
		this.keyHandler = keyHandler;
		this.gameStateManager = gameStateManager;
		// Level akan default berada di level 1
		this.currSelection = 1;
		this.currLevel = LoadSave.loadLevelProgress();
		
		this.textures = textures;
		//title level
		this.titleImage = textures.getTitleTextures().get(1);
		
		BufferedImage idleButtonImage = this.textures.getButtonTextures().get(4); // button if not touch
		BufferedImage activeButtonImage = this.textures.getButtonTextures().get(5); // button if touched
		BufferedImage lockedButtonImage = this.textures.getButtonTextures().get(6); // button if there no level
		// untuk mengatur jumlah level
		this.buttons = new ArrayList<MenuButton>();
		for(int i = 0; i < rows; i++) {
			for(int j = 0; j < cols; j++) {
				System.out.println(3*i+j+1 + ">" + currLevel);
				int status = (3*i+j+1 > currLevel || 3*i+j+1 > maxLevel) ? MenuButton.LOCKED : MenuButton.UNLOCKED;
				System.out.println(status);
				buttons.add(new MenuButton(GamePanel.WIDTH/2 - ((cols * activeButtonImage.getWidth() 
						* GamePanel.SCALE + (cols-1) * distance))/2 + j * (distance + 
						activeButtonImage.getWidth() * GamePanel.SCALE), 
						GamePanel.HEIGHT/2 - (rows * activeButtonImage.getHeight()*GamePanel.SCALE + 
						(rows-1) * distance)/2 + i*(distance + activeButtonImage.getHeight() * GamePanel.SCALE)
						+ distance,idleButtonImage,activeButtonImage,3*i+j+1,textures.getNumberTextures(),
						status,lockedButtonImage));
			}
		}
	}
	
	
	//UPDATE
	public void update() {
		if(keyHandler.getW() && currSelection >= 4) { // currlevelnya lebih dari 4 sehingga berada di kolom 2
			keyHandler.setW(false);
			currSelection -= 3; // level akan dikurangi 3 (naik posisi 1 kali)
		}
		if(keyHandler.getA() && currSelection > 1) { // currlevelnya harus lebih dari 1 sehingga bisa dikurangi
			keyHandler.setA(false);
			currSelection--;
		}
		if(keyHandler.getS() && currSelection <= maxLevel - 3) { // currlevel harus kurang dari 5
			keyHandler.setS(false);
			currSelection += 3; //sehingga level ditambah 3 (turun 1 posisi)
		}
		if(keyHandler.getD() && currSelection < maxLevel) { //currlevel harus kurang dari 5
			keyHandler.setD(false);
			currSelection++; //sehingga currlevel ditambah
		}
		if(( keyHandler.getEnter())  && currSelection <= currLevel) {
			keyHandler.setEnter(false);
			
			
			gameStateManager.getPlayState().setCurrentLevel(currSelection);
			gameStateManager.getPlayState().levelInit();
			gameStateManager.setCurrentState(GameStateManager.PLAYSTATE); // langsung menuju play state
		}
	}

	
	//DRAW
	public void draw(Graphics g) {
		g.setColor(new Color(23, 32, 56));
		g.fillRect(0, 0, GamePanel.WIDTH, GamePanel.HEIGHT);
		
		g.drawImage(titleImage, GamePanel.WIDTH/2 - titleImage.getWidth()*GamePanel.SCALE/2, 15, titleImage.getWidth() * GamePanel.SCALE, titleImage.getHeight() * GamePanel.SCALE, null);
		
		for(MenuButton button : buttons) {
			button.draw(g, currSelection);
		}
	}
	
}
