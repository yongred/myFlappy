package myFlappyBird;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.Timer;

public class DrawPanel extends JPanel implements ActionListener{
	
	private Timer timer;
	private Bird bird;
	private ArrayList<Rectangle> pipeColumns;
	
	static final int WIDTH = 800, HEIGHT = 600;
	private boolean running = false;
	
	private BufferedImage background = null;
	private BufferedImage spriteSheet= null;
	
	public DrawPanel (){
		initBoard();
	}
	
	public void initBoard(){
		
		addKeyListener(new myAdapter());
		BufferedImageLoader loader = new BufferedImageLoader();
		
		try{
			setSpriteSheet(loader.loadImage("/sprite_Sheet.png"));
			background = loader.loadImage("/background.png");
		}catch(IOException e){
			e.printStackTrace();
		}
		
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		setDoubleBuffered(true);
		setFocusable(true);
		
		bird = new Bird(100, 200, this);
		timer = new Timer(5, this);
        timer.start();
	}
	
	public BufferedImage getSpriteSheet() {
		return spriteSheet;
	}

	public void setSpriteSheet(BufferedImage spriteSheet) {
		this.spriteSheet = spriteSheet;
	}
	
	@Override
	public void paintComponent(Graphics g) {
        super.paintComponent(g); 
        Graphics2D g2d = (Graphics2D) g;
        render(g2d);
	}
	
	public void render(Graphics g){
		g.drawImage(background, 0, 0, this);
		g.drawImage(bird.getBirdImg(), (int)bird.getX(), (int)bird.getY(), this);
		
		//.............................
		Toolkit.getDefaultToolkit().sync();
        g.dispose();
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		bird.tick();
		repaint();
	}
	
	private class myAdapter extends KeyAdapter {
		 public void keyReleased(KeyEvent e) {
	            bird.keyReleased(e);
	        }

	        public void keyPressed(KeyEvent e) {
	            bird.keyPressed(e);
	        }
	}
}
