package myFlappyBird;

import java.awt.Rectangle;
import java.util.Random;

public class PipeCol extends Rectangle{
	
	private int speed = 5;
	
	public PipeCol(int x, int y, int width, int height){
		super(x, y, width, height);
	}
	
	public void tick(){
		this.x -= speed;
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}
}
