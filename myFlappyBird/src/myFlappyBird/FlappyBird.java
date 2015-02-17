package myFlappyBird;

import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.swing.JFrame;

public class FlappyBird extends JFrame{
	
	private DrawPanel drawSpace;
	
	public FlappyBird(){
		
		init();
	}
	
	public void init(){
		
		setTitle("Flappy Bird");
		drawSpace = new DrawPanel();
		add(drawSpace);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(800, 600);
		setResizable(false);
		setVisible(true);
		
	}
	

	
	static public void main(String [] args){
		FlappyBird flappy = new FlappyBird(); 
	}
}
