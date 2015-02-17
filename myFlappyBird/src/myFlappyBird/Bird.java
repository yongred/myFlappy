package myFlappyBird;

import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

public class Bird {
	
	private DrawPanel gamePanel;
	private double dy;

	private double x;
	private double y;
	
	private boolean is_jumping = false;
	private BufferedImage birdImg;
	
	public Bird(double x, double y, DrawPanel gamePanel){
		this.x = x;
		this.y = y;
		this.gamePanel = gamePanel;
		SpriteSheet ss = new SpriteSheet(gamePanel.getSpriteSheet());
		birdImg = ss.grabImage(1, 1, 32, 32);
	}
	
	public void tick(){
		
		y += dy;
	}
	
	public void jump(){
		dy = -10;
	}
	
	public void sink(){
		dy = 2;
	}

	public void keyPressed(KeyEvent e){
		int key = e.getKeyCode();
		
		if(key == KeyEvent.VK_SPACE && !is_jumping){
			jump();
			is_jumping = true;
		} 
	}
	
	public void keyReleased(KeyEvent e){
		int key = e.getKeyCode();
		
		if(key == KeyEvent.VK_SPACE){
			sink();
			is_jumping = false;
		}
	}
	
	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}
	
	public double getDy() {
		return dy;
	}

	public void setDy(double dy) {
		this.dy = dy;
	}

	public BufferedImage getBirdImg() {
		return birdImg;
	}

	public void setBirdImg(BufferedImage birdImg) {
		this.birdImg = birdImg;
	}
	
}
