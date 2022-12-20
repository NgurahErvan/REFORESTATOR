package state;

import java.awt.Graphics;

import input.KeyHandler;
import util.ImageLoader;
import util.Image;

public class GameStateManager {
	// untuk mengecek current state dari masing masing state
	public final static int MENUSTATE = 0;
	public final static int LEVELMENUSTATE = 1;
	public final static int PLAYSTATE = 2;
	public final static int GAMEOVERSTATE = 3;
	public final static int LEVELCOMPLETESTATE = 4;
	
	GameState[] gameStates = new GameState[5];
	// tampilan pertama akan berada di MENUSTATE
	private int currentState = MENUSTATE;
	private ImageLoader loader = new ImageLoader();
	private Image textures = new Image(loader);
	//constructor
	public GameStateManager(KeyHandler keyHandler) {
		gameStates[MENUSTATE] = new FirstMenu(keyHandler, this, textures);
		gameStates[LEVELMENUSTATE] = new MenuLevel(keyHandler, this, textures);
		gameStates[PLAYSTATE] = new PlayState(keyHandler, this, loader, textures);
		gameStates[GAMEOVERSTATE] = new GameOver(keyHandler, this, loader, textures);
		gameStates[LEVELCOMPLETESTATE] = new FinishedState(keyHandler, this, textures);
	}

	
	//GETTERS-N-SETTERS
	public void setCurrentState(int currentMenu) {currentState = currentMenu;}
	public PlayState getPlayState() {return (PlayState) gameStates[PLAYSTATE];}
	
	
	//UPDATE
	public void update() {
		gameStates[currentState].update();
	}
	
	
	//DRAW
	public void draw(Graphics g) {
		gameStates[currentState].draw(g);
	}
}
