package tile;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import entites.SuperEntityObject;
// untuk memasukan TILESIZE di class game
import main.GamePanel;

public class Bricks extends SuperEntityObject{
	private int x, y;
	private int width, height;
	private int entityID;
	
	private BufferedImage texture;
	
	
	public Bricks(int x, int y,  ArrayList<BufferedImage> blockTextures) {
		this.x = x;
		this.y = y;
		this.width = GamePanel.TILESIZE; //32
		this.height = GamePanel.TILESIZE; //32
		
		this.texture = blockTextures.get(1);
		
		this.entityID = BLOCK;
	}
	
	//SETTER AND GETTER
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	public int getEntityID() {
		return entityID;
	}
	public void setEntityID(int object) {
		this.entityID = object;
	}
	
	@Override
	public int getHealth() {return 0;}
	
	
	public void draw(Graphics g) {
		
		try {
			g.drawImage(this.texture, x, y, width, height, null);
		}catch(Exception e) {
		
			g.fillRect(x, y, width, height);
			
			g.drawRect(x,y,width,height);
		}
	}

	@Override
	public void update() {}

	@Override
	public Rectangle getTopBound() {
		return new Rectangle(this.x, this.y, this.width, this.height);
	}

	@Override
	public Rectangle getRightBound() {
		return new Rectangle(this.x, this.y, this.width, this.height);
	}

	@Override
	public Rectangle getBottomBound() {
		return new Rectangle(this.x, this.y, this.width, this.height);
	}

	@Override
	public Rectangle getLeftBound() {
		return new Rectangle(this.x, this.y, this.width, this.height);
	}
}
