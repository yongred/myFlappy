package myFlappyBird;

import java.awt.Rectangle;

public class PipeCol extends Rectangle{
	
	public PipeCol(int x, int y, int width, int height){
		super(x, y, width, height);
	}
	
	public void tick(){
		this.x ++;
	}
}
