package physicsEngine;

import java.util.ArrayList;

import operations.Operation;
import renderEngine.Render;

public class Engine implements Runnable {
	
	private boolean running = true;
	public static double timeIncrement;
	public static Operation x, y, z;
	
	public Engine(int particles){
		ParticleField.createField(particles);
	}
	
	/**
	 * A loop that ticks every 60 seconds for moving particles. 
	 * This also displays the ticks and how many spare loops it was able to go through while waiting for the next tick. 
	 */

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
			
			while(running){
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
					System.out.println("Engine Ticks: " + ticks + " Loops: " + loops);
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
	
	/**
	 * Manipulates the variables that change over time.
	 */
	
	private void tick(){
		while(ParticleField.cycleAndIfHasNext()){
			Particle part = ParticleField.getCurrentParticle();
			part.x = x.operate();
			part.y = y.operate();
			part.z = z.operate();
			part.time += timeIncrement;
		}
	}


}
