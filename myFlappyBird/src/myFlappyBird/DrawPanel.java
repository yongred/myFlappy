package myFlappyBird;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.swing.JPanel;

public class DrawPanel extends JPanel{
	
	private FlappyBird flappy;
	private Bird bird;
	
	static final int WIDTH = 800, HEIGHT = 600;
	private boolean running = false;
	
	private BufferedImage background = null;
	private BufferedImage buffer = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
	private BufferedImage spriteSheet= null;
	
	public DrawPanel (){
		initBoard();
	}
	
	public void initBoard(){
		
		BufferedImageLoader loader = new BufferedImageLoader();
		
		try{
			setSpriteSheet(loader.loadImage("/sprite_Sheet.png"));
			background = loader.loadImage("/background.png");
		}catch(IOException e){
			e.printStackTrace();
		}
		
	}
	
	public void render(Graphics g){
		
		g.drawImage(background, 0, 0, null);

	}
	
	public BufferedImage getSpriteSheet() {
		return spriteSheet;
	}

	public void setSpriteSheet(BufferedImage spriteSheet) {
		this.spriteSheet = spriteSheet;
	}
	
	public void paintComponent(Graphics g) {
        super.paintComponent(g);   
        render(g);
	}
}
