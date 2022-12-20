package tile;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import main.GamePanel;

public class Heart {
	private int x, y;
	private ArrayList<BufferedImage> textures;
	private int id;
	
	public Heart(int x, int y, ArrayList<BufferedImage> heartImg, int id) {
		this.x = x;
		this.y = y;
		this.textures = heartImg;
		this.id = id;
	}
	
	public void draw(Graphics g, int healthAmount) {
		if(healthAmount >= id) {
			g.drawImage(textures.get(1), x, y, textures.get(1).getWidth() * GamePanel.SCALE, 
					textures.get(1).getHeight() * GamePanel.SCALE,null);
		}else {
			g.drawImage(textures.get(0), x, y, textures.get(0).getWidth() * GamePanel.SCALE, 
					textures.get(0).getHeight() * GamePanel.SCALE,null);
		}
	}
}
