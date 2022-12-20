package entites;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import input.KeyHandler;
import main.GamePanel;
import state.GameStateManager;
import util.Animation;
import util.Image;

public class Player extends SuperEntityObject implements Collide{
	//Variable all entity
	private int x, y;
	private float velX, velY;
	private int entityID;
	
	private int width, height;
	private int health = 5;
	
	private float gravity = 0.3F;
	// Player akan menghadap kanan ketika awal game
	private boolean facingRight = true;
	private boolean falling = true;
	private boolean jumping = false;
	private boolean invincible = false;
	private boolean Got_Hit = false;
	private int invincibleCounter = 0;
	
	private KeyHandler keyHandler;
	private Handler handler;
	private ArrayList<BufferedImage> textures;
	// variable from Animation class
	private Animation currAnimation; // player start with Stop_Right
	private Animation Stop_Right; //when player stop right
	private Animation Stop_Left; // when player stop left
	private Animation Run_Left; // when player move left
	private Animation Run_Right; // when player move right
	private Animation Hit_Stop_Right; // When player got a hit by enemy right
	private Animation Hit_Stop_Left; // When player got a hit by enemy right
	private Animation Hit_Run_Left; // When player got a hit by enemy right
	private Animation Hit_Run_Right; // When player got a hit by enemy right
	
	private GameStateManager gsm;
	
	//constructor
	public Player(int x, int y, KeyHandler keyHandler, Handler handler, Image textures, GameStateManager gsm) {
		this.x = x;
		this.y = y;
		this.width = 15 * GamePanel.SCALE; // lebar 30
		this.height = 31 * GamePanel.SCALE; // 62
		
		this.keyHandler = keyHandler;
		this.entityID = PLAYER;
		this.handler = handler;
		// menambahkan animasi setiap player diam atau bergerak
		this.textures = textures.getPlayerTextures();
		this.Stop_Right = new Animation(10, 0, 8, this.textures); //int frameDuration, int startIndex, int length, ArrayList<BufferedImage> animationSet)
		this.Stop_Left = new Animation(10, 4, 8, this.textures);
		this.Run_Right = new Animation(5, 8, 8, this.textures);
		this.Run_Left = new Animation(5, 16, 8, this.textures);
		// menambahkan animasi ketika setiap player got hit by enemy
		this.Hit_Stop_Right = new Animation(10, 24, 8, this.textures);
		this.Hit_Stop_Left = new Animation(10, 28, 8, this.textures);
		this.Hit_Run_Right = new Animation(5, 32, 8, this.textures);
		this.Hit_Run_Left = new Animation(5, 40, 8, this.textures);

		
		this.currAnimation = Stop_Right; // player start with Stop_Right
		this.gsm = gsm;
	}
	
	
	//GETTERS-N-SETTERS
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
	public int getHealth() {
		return health;
	}
	
	
	//COLLISION BOUND GETTERS
	public Rectangle getTopBound() {
		return new Rectangle(this.x + this.width/6, this.y-1, 2*this.width/3, this.height/10);
	}
	public Rectangle getRightBound() {
		return new Rectangle(this.x + 7*this.width/8, this.y+this.height/6, this.width/8, 2*this.height/3);
	}
	public Rectangle getBottomBound() {
		return new Rectangle(this.x + this.width/6, this.y+9*this.height/10+1, 2*this.width/3, this.height/10);
	}
	public Rectangle getLeftBound() {
		return new Rectangle(this.x-1, this.y+this.height/6, this.width/8, 2*this.height/3);
	}
	
	
	//DRAW CLASS
	public void draw(Graphics g) {
		playerDraw(g, currAnimation.getCurrFrame());
	}
	private void playerDraw(Graphics g, BufferedImage imageToDraw) {
		g.drawImage(imageToDraw, x - (imageToDraw.getWidth()*GamePanel.SCALE-width)/2, 
				y - (imageToDraw.getHeight()*GamePanel.SCALE-height), imageToDraw.getWidth()
				* GamePanel.SCALE, imageToDraw.getHeight() * GamePanel.SCALE, null);
	}
	
	
	//UPDATE
	public void update() {
		invincibleTimer(); // berfungsi untuk mengurangi jumlah nyawa
		playerDead();
		velocity_Player();
		
		if(falling) velY += gravity;
		
		x += velX;
		y += velY;
		
		collide();
		
		currAnimation.runAnimation();
	}
	// to set the velocity of the player
	private void velocity_Player() {
		// when the keyboard pressed w player will jumping and falling
		if(keyHandler.getW() && !jumping) {
			velY = -7;
			jumping = true;
			falling = true;
		}
		// when the keyboard pressed a player will velocity X will decrease by 4 
		if(keyHandler.getA()) {
			velX = -4;
			if(!Got_Hit) currAnimation = Run_Left; 
			else currAnimation = Hit_Run_Left;
			facingRight = false;
		}
		// when the keyboard pressed D/s player will velocity X will increase by 4 
		if(keyHandler.getS()) velY = 4;
		if(keyHandler.getD()) {
			velX = 4;
			if(!Got_Hit) currAnimation = Run_Right;
			else currAnimation = Hit_Run_Right;
			facingRight = true;
		}
		// when the player not move
		if(!keyHandler.getW() && !keyHandler.getS() && !falling) velY = 0;
		if(!keyHandler.getA() && !keyHandler.getD()) {
			velX = 0;
			if(!Got_Hit) {
				if(facingRight) currAnimation = Stop_Right;
				else currAnimation = Stop_Left;
			} else {
				if(facingRight) currAnimation = Hit_Stop_Right;
				else currAnimation = Hit_Stop_Left;
			}
		}
	}
	// for count the player heart
	private void invincibleTimer() {
		if(invincibleCounter > 0) invincibleCounter--;
		if(invincibleCounter <= 0) {
			Got_Hit = false;
			invincible = false;
		}
	}
	
	
	//for set the collision between player and the other entities
	public void collide() {
		for(SuperEntityObject x : handler.getEntities()) {
			// when the player touch the bricks
			if(x.getEntityID() == BLOCK) {
				if(this.getTopBound().intersects(x.getBottomBound())) {
					velY = 0;
					this.y = x.getY() + x.getHeight() + 1;
				}if(this.getBottomBound().intersects(x.getTopBound())) {
					velY = 0;
					this.jumping = false;
					this.y = x.getY() - this.getHeight();
				}
				
				if(this.getLeftBound().intersects(x.getRightBound())) {
					velX = 0;
					this.x = x.getX() + x.getWidth() + 1;
				}if(this.getRightBound().intersects(x.getLeftBound())) {
					velX = 0;
					this.x = x.getX() - this.getWidth();
				}
			}
			// ketika player terkena enemy atau obstacle yang ada
			if(x.getEntityID() == OBSTACLE || x.getEntityID() == ENEMY) {
				if(this.getTopBound().intersects(x.getBottomBound()) ||
					this.getBottomBound().intersects(x.getTopBound()) ||
					this.getLeftBound().intersects(x.getRightBound()) ||
					this.getRightBound().intersects(x.getLeftBound())) {
					
					if(!invincible) {
						this.health--;
						Got_Hit = true;
						invincible = true;
						invincibleCounter = 20;
					}
				}
			}
			// ketika telah mencapai tujuan maka player akan menampulkan animasi level complete
			if(x.getEntityID() == FLAG) {
				if(this.getTopBound().intersects(x.getBottomBound()) ||
					this.getBottomBound().intersects(x.getTopBound()) ||
					this.getLeftBound().intersects(x.getRightBound()) ||
					this.getRightBound().intersects(x.getLeftBound())) {
					velX = 0;
					velY = 0;
					this.falling = false;
					
					gsm.setCurrentState(GameStateManager.LEVELCOMPLETESTATE);
				}
			}
		}
	}
	
	
	// game akan game over ketika health kurang dari 0
	public void playerDead() {
		if(playerFalling() || health <= 0) {
			GameOver();
		}
	}
	// game akan memunculkan game over ketika player jatuh lebih dari kitinggian 4000
	private boolean playerFalling() {
		if(y > 4000) {
			return true;
		}return false;
	}
	// menampilkan tampilan game over pada gamestatemanager yang akan dibawa ke player dead
	private void GameOver() {
		gsm.setCurrentState(GameStateManager.GAMEOVERSTATE);
	}
}