package renderEngine;

import java.awt.Canvas;
import java.awt.Dimension;

import javax.swing.JFrame;

public class Display implements Runnable{
	
	private static JFrame frame;
	private static Canvas canvas;
	public static int width, height;
	
	public Display(){
		width = 640;
		height = 360;
		createDisplay();
	}
	
	public void createDisplay(){
		frame = new JFrame("Particle Field");
		frame.setSize(width, height);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(true);
		frame.setMinimumSize(new Dimension(640, 360));
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		canvas = new Canvas();
		canvas.setSize(width, height);
		frame.add(canvas);
		frame.pack();
	}
	

	public void run() {
		try {
			
			int fps = 60;
			double timePerTick = 1000000000 / fps;
			double delta = 0;
			long now;
			long lastTime = System.nanoTime();
			long timer = 0;
			int ticks = 0;
			long loops = 0;
			
			while(true){
				now = System.nanoTime();
				delta += (now - lastTime) / timePerTick;
				timer += now - lastTime;
				lastTime = now;
				loops++;
				
				if(delta >= 1){
					tick();
					ticks++;
					delta--;
				}
				
				if(timer >= 1000000000){
					System.out.println("Ticks: " + ticks + " Loops: " + loops);
					ticks = 0;
					timer = 0;
					loops = 0;
				}
					
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	private void tick(){
		Render.mainRender(canvas);
	}

}
