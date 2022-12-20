package input;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyHandler extends KeyAdapter{
	
	private boolean up = false,
			left = false,
			down = false,
			right = false,
			Enter = false;
	
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		
		if(key == KeyEvent.VK_UP) up = true ;
		if(key == KeyEvent.VK_LEFT) left = true;
		if(key == KeyEvent.VK_DOWN) down = true;
		if(key == KeyEvent.VK_RIGHT) right = true;
		if(key == KeyEvent.VK_ENTER) Enter = true;
	}
	
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		
		if(key == KeyEvent.VK_UP) up = false;
		if(key == KeyEvent.VK_LEFT) left = false;
		if(key == KeyEvent.VK_DOWN) down = false;
		if(key == KeyEvent.VK_RIGHT) right = false;
		if(key == KeyEvent.VK_ENTER) Enter = false;
	}
	
	public boolean getW() {return up;}
	public boolean getA() {return left;}
	public boolean getS() {return down;}
	public boolean getD() {return right;}

	public boolean getEnter() {return Enter;}

	public void setW(boolean b) {this.up = b;}
	public void setA(boolean b) {this.left = b;}
	public void setS(boolean b) {this.down = b;}
	public void setD(boolean b) {this.right = b;}

	public void setEnter(boolean b) {this.Enter = b;}
}
