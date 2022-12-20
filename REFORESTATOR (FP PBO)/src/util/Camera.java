package util;

import entites.SuperEntityObject;
import main.GamePanel;

public class Camera {
	
	private int x;
	private int y;
	
	public Camera(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public int getX() {return x;}
	public void setX(int x) {this.x = x;}
	public int getY() {return y;}
	public void setY(int y) {this.y = y;}
	
	public void update(SuperEntityObject player) {
		if(player.getX() >= GamePanel.WIDTH/2)  this.x = -player.getX() + GamePanel.WIDTH/2;
		this.y = -player.getY() + GamePanel.HEIGHT/2;
	}
}
