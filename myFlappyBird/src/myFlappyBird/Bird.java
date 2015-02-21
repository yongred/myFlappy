package myFlappyBird;

import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

public class Bird {
	
	private DrawPanel gamePanel;
	private int dy;
	
	private int width = 64;
	private int height = 64;
	private int x;
	private int y;
	
	private SpriteSheet ss;
	private boolean is_jumping = false;
	private BufferedImage birdImg;
	
	public Bird(int x, int y, DrawPanel gamePanel){
		this.x = x;
		this.y = y;
		this.gamePanel = gamePanel;
		this.ss = new SpriteSheet(gamePanel.getSpriteSheet());
		birdImg = ss.grabImage(1, 1, 64, 64);
	}
	
	public void tick(){
		
		y += dy;
	}
	
	public void jump(){
		dy = -10;
	}
	
	public void sink(){
		dy = 5;
	}
	
	public boolean isVisible(){
		boolean visible;
		if(y + height >= gamePanel.getHeight() || y <= 0 ){
			visible = false;
		} else{
			visible = true;
		}
		return visible;
	}

	public void keyPressed(KeyEvent e){
		int key = e.getKeyCode();
		
		if(key == KeyEvent.VK_SPACE){
			jump();
		} 
	}
	
	public void keyReleased(KeyEvent e){
		int key = e.getKeyCode();
		
		if(key == KeyEvent.VK_SPACE){
			sink();
		}
	}
	
	public Rectangle getBounds(){
		return new Rectangle(x, y, width, height);
	}
	
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
	
	public int getDy() {
		return dy;
	}

	public void setDy(int dy) {
		this.dy = dy;
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

	public BufferedImage getBirdImg() {
		return birdImg;
	}

	public void setBirdImg(BufferedImage birdImg) {
		this.birdImg = birdImg;
	}
	
	public void setBirdImgColRow(int col, int row){
		this.birdImg = this.ss.grabImage(col, row, 64, 64);
	}
}
