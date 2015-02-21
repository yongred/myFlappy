package myFlappyBird;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JPanel;
import javax.swing.Timer;

public class DrawPanel extends JPanel implements ActionListener{
	
	private Timer timer;
	private Bird bird;
	private ArrayList<Rectangle> pipeColumns;
	
	static final int WIDTH = 800, HEIGHT = 600;
	private int score;
	private int highScore;
	private boolean inGame;
	private Random rand;

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
		
		inGame = true;
		score = 0;
		rand = new Random();
		pipeColumns = new ArrayList<Rectangle>();
		bird = new Bird(100, 200, this);
		timer = new Timer(5, this);
		
		for(int i =0; i < 4; i++){
			addCol();
		}
        //timer.start();
	}
	
	public void addCol(){
		int space = 200;
		int width = 100;
		int height = 100 + rand.nextInt(200);
		
		if(pipeColumns.size() == 0){
			pipeColumns.add(new PipeCol(750, 0, width, HEIGHT - height - space));
			pipeColumns.add(new PipeCol(750, HEIGHT - height, width, height - 59));
		} else {
			int relativeX = pipeColumns.get((pipeColumns.size()-1)).x + 400;
			pipeColumns.add(new PipeCol(relativeX, 0, width, HEIGHT - height - space));
			pipeColumns.add(new PipeCol(relativeX, HEIGHT - height, width, height - 59));
		}
	}
	
	public void paintCol(Graphics g, Rectangle pc){
		g.setColor(Color.green.darker());
		g.fillRect(pc.x, pc.y, pc.width, pc.height);
	}
	
	@Override
	public void paintComponent(Graphics g) {
        super.paintComponent(g); 
        Graphics2D g2d = (Graphics2D) g;
        render(g2d);
	}
	
	public void render(Graphics g){
		g.drawImage(background, 0, 0, this);
		g.setFont(new Font("TimesRoman", Font.BOLD, 20)); 
		g.drawString(String.valueOf(highScore) , 400, 250);
		if(!timer.isRunning()){
			g.drawString("Press enter to pause/start!", 400, 400);
			g.drawString("press space_bar to jump", 400, 350);
		}
		
		if(inGame)
		{
			g.drawImage(bird.getBirdImg(), bird.getX(), bird.getY(), this);
			g.drawString(String.valueOf(score) , 400, 300);
			for(int x = 0; x< pipeColumns.size(); x++){
				paintCol(g, pipeColumns.get(x));
			}
		}else {
			g.drawString("collide!!", 400, 300);
		}
		//.............................
		Toolkit.getDefaultToolkit().sync();
        g.dispose();
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		if(inGame){
			bird.tick();
		
			while(pipeColumns.size()< 4){
				addCol();
			}
		
			for(int x = 0; x< pipeColumns.size(); x++){
				PipeCol pc = (PipeCol) pipeColumns.get(x);
				pc.tick();
				if(!pc.isVisible()){
					pipeColumns.remove(pc);
				}
			}
			checkCollision();
			
		} else{
			timer.stop();
			scoreSave(score);
			reset();
		}
		repaint();
	}
	
	public void reset(){
		
		pipeColumns.clear();
		bird = new Bird(100, 200, this);
		score = 0;
	}
	
	public void checkCollision(){
		Rectangle birdBound = bird.getBounds();
		
		if(birdBound.y + birdBound.height >= HEIGHT - 59){
			bird.setY(HEIGHT - birdBound.height - 59);
			//inGame = false;
		}
		
		if(birdBound.y <= 0){
			bird.setY(0);
		}
		//PipeCol tempPC = (PipeCol) pipeColumns.get(0);
		boolean scored = false;
		for(int x = 0; x< pipeColumns.size(); x++){
			Rectangle pipeBound = pipeColumns.get(x).getBounds();
			if(birdBound.intersects(pipeBound)){
				inGame = false;
			} else if ((pipeBound.y == 0 ) && (birdBound.x == pipeBound.x + pipeBound.width/2)){
				score++;
			}

		}
		
	}

	private class myAdapter extends KeyAdapter {
		 public void keyReleased(KeyEvent e) {
	            bird.keyReleased(e);
	        }

	        public void keyPressed(KeyEvent e) {
	        	if(e.getKeyCode() == KeyEvent.VK_ENTER){
	        		if(!inGame){
	        			inGame = true;
	        		}
	        		if(timer.isRunning()){
	        			timer.stop();
	        		} else {
	        			timer.restart();
	        		}
	        	}
	        	else
	        		bird.keyPressed(e);
	        }
	        
	}
	
	public void changeBird(int col, int row){
		bird.setBirdImgColRow(col, row);
	}
	
	public void scoreSave(int hScore) {
		
		try {
			FileWriter fw = new FileWriter("highScore.txt");
			FileReader fr = new FileReader("highScore.txt");
			PrintWriter pw = new PrintWriter(fw);
			BufferedReader br = new BufferedReader(fr);
			int hs = 0;
			int temp = 0;
			String str;
			while((str = br.readLine()) != null){
				temp = Integer.parseInt(str);
				if(temp > hs){
					hs = temp;
				}
			}
			if(hScore > hs){
				pw.println(hScore);
				this.highScore = hScore;
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	
	public BufferedImage getSpriteSheet() {
		return spriteSheet;
	}

	public void setSpriteSheet(BufferedImage spriteSheet) {
		this.spriteSheet = spriteSheet;
	}
}
