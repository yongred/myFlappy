package myFlappyBird;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;

public class Bird {
	
	private double x;
	private double y;
	
	private BufferedImage bird;
	
	public Bird(double x, double y, FlappyBird flappy){
		this.x = x;
		this.y = y;
		this.flappy = flappy;
		
		SpriteSheet ss = new SpriteSheet(flappy.getSpriteSheet());
		bird = ss.grabImage(1, 1, 63, 63);
	}
	
	public void render(Graphics g){
		g.drawImage(bird, (int)x, (int)y, null);
	}
}
