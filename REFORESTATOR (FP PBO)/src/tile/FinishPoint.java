package tile;

import java.awt.Graphics;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import entites.SuperEntityObject;
import main.GamePanel;
import util.Animation;

public class FinishPoint extends SuperEntityObject{
	private int x, y;
	private int width, height;
	private int entityID;
	
	private ArrayList<BufferedImage> textures;
	private Animation animation;
	
	public FinishPoint(int x, int y, ArrayList<BufferedImage> textures) {
		this.x = x;
		this.y = y;
		this.width = GamePanel.TILESIZE;
		this.height = GamePanel.TILESIZE * 2;
		
		this.entityID = FLAG;
		
		this.textures = textures;
		this.animation = new Animation(10, 0, 6, this.textures); 
	}
	
	//GETTERS-N-SETTERS
	public int getX() {return x;}
	public void setX(int x) {this.x = x;}
	public int getY() {return y;}
	public void setY(int y) {this.y = y;}
	public int getWidth() {return width;}
	public void setWidth(int width) {this.width = width;}
	public int getHeight() {return height;}
	public void setHeight(int height) {this.height = height;}
	public int getEntityID() {return entityID;}
	public void setEntityID(int object) {this.entityID = object;}
	public int getHealth() {return 0;}
	
	
	//UPDATE
	public void update() {animation.runAnimation();}

	
	//DRAW
	public void draw(Graphics g) {
		g.drawImage(animation.getCurrFrame(), x, y, width, height, null);
	}
	
	
	//COLLISION-BOUND GETTERS
	public Rectangle getTopBound() {
		return new Rectangle(this.x, this.y, this.width, this.height);
	}
	public Rectangle getRightBound() {
		return new Rectangle(this.x, this.y, this.width, this.height);
	}
	public Rectangle getBottomBound() {
		return new Rectangle(this.x, this.y, this.width, this.height);
	}
	public Rectangle getLeftBound() {
		return new Rectangle(this.x, this.y, this.width, this.height);
	}
}
