package state;
//Super Class dari package state ini
import java.awt.Graphics;

import input.KeyHandler;
import util.Image;

public abstract class GameState {
	protected KeyHandler keyHandler;
	protected GameStateManager gameStateManager;
	protected Image textures;
	
	public final static int MAXLEVEL = 8;
	
	abstract public void update();
	abstract public void draw(Graphics g);
}
