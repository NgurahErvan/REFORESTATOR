package state;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import input.KeyHandler;
import main.GamePanel;
import util.Animation;
import util.Image;
	//Kelas untuk mengatur menu awal ketika game di run
public class FirstMenu extends GameState{
	//BUTTON CODEs
	public final static int PLAY = 1;
	public final static int EXIT = 2;
	
	private int currentMenu = 0;
	private int menus = 2;
	
	private Image textures;
	private ArrayList<BufferedImage> buttonTextures;
	private ArrayList<BufferedImage> menuTextures;
	
	private BufferedImage titleImage;
	
	private Animation menuAnimation;
	private Animation menuShineAnimation;
	private int playShineCounter = 0;

	private ArrayList<MenuButton> buttons = new ArrayList<MenuButton>();
	
	public FirstMenu(KeyHandler keyHandler, GameStateManager gameStateManager, Image textures) {
		this.keyHandler = keyHandler;
		this.gameStateManager = gameStateManager;
		this.textures = textures;
		//Animation Texture
		this.titleImage = this.textures.getTitleTextures().get(0);
		this.menuTextures = this.textures.getMenuTextures();
		this.buttonTextures = this.textures.getButtonTextures();
		// AMenu Animation when start the game
		this.menuAnimation = new Animation(10, 0, 4, menuTextures); // int frameDuration, int startIndex, int length, ArrayList<BufferedImage> animationSet)
		this.menuShineAnimation = new Animation(10, 4, 4, menuTextures);
		
		//assuming buttons are uniform size and don't change
		int buttonWidth = buttonTextures.get(0).getWidth() * GamePanel.SCALE;
		// jarak between 1 button and the other
		int margin = 40;
		int x = GamePanel.WIDTH/2 - (buttonWidth*menus + margin * (menus-1))/2;
		// Play Button
		buttons.add(new MenuButton(x, GamePanel.HEIGHT - 50, buttonTextures.get(0), buttonTextures.get(1), 
				PLAY, null, MenuButton.UNLOCKED, null));
		//Exit Button
		buttons.add(new MenuButton(x + (buttonWidth + margin), GamePanel.HEIGHT - 50, buttonTextures.get(2), 
				buttonTextures.get(3), EXIT, null, MenuButton.UNLOCKED, null));
	}
	
	
	//UPDATE
	public void update() {
		// TODO Auto-generated method stub
		if((keyHandler.getW() || keyHandler.getS() || keyHandler.getA() || keyHandler.getD()) && (currentMenu < 1 || currentMenu > menus)) currentMenu = 1;
		// Bergerak kekiri
		// setelah melalui kondisi if pertama maka currentMenu = 1 ; menus = 2
		// button tidak bergerak ketika sudah berada di kiri karena current menu = 1
		if((keyHandler.getW() || keyHandler.getA()) && currentMenu != 1) { 
			currentMenu--;
			keyHandler.setA(false);
			keyHandler.setW(false);
		//Bergerak ke kanan
		// button tidak bergerak ketika sudah berada di kiri karena current menu = 1
		}
		if((keyHandler.getS() || keyHandler.getD()) && currentMenu != menus) {
			currentMenu++;
			keyHandler.setD(false);
			keyHandler.setS(false);
		// Menuju ke menu selanjutnya
		}if( keyHandler.getEnter()) {
			keyHandler.setEnter(false);
			
			System.out.println("ENTER WAS PRESSED IN MENU");
			// Jika Current Menu menuju exit maka akan keluar game
			if(currentMenu == EXIT) {
				System.exit(0);
			}
			// jika cuurentMenu mengarah ke play maka menuju levelmenustate
			else if(currentMenu == PLAY) gameStateManager.setCurrentState(GameStateManager.LEVELMENUSTATE);
		}
		
		playShineCounter++;
		if(playShineCounter > 120) playShineCounter = 0;
		menuAnimation.runAnimation();
		menuShineAnimation.runAnimation();
	}


	//DRAW
	public void draw(Graphics g) {
		// TODO Auto-generated method stub
		if(playShineCounter >= 100 && playShineCounter <= 120) {
			g.drawImage(menuShineAnimation.getCurrFrame(), 0, 0, GamePanel.WIDTH, GamePanel.HEIGHT, null);
		}
		else g.drawImage(menuAnimation.getCurrFrame(), 0, 0, GamePanel.WIDTH, GamePanel.HEIGHT, null);
		
		g.drawImage(titleImage, 
				GamePanel.WIDTH/2 - titleImage.getWidth(), 10, titleImage.getWidth()*GamePanel.SCALE, 
				titleImage.getHeight()*GamePanel.SCALE, null);
		
		for(MenuButton x : buttons) {
			x.draw(g, currentMenu);
		}
	}
	
	
}
