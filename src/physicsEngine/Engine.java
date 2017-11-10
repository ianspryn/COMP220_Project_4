package physicsEngine;

import java.util.ArrayList;

import renderEngine.Render;

public class Engine implements Runnable {
	
	private boolean running = true;
	
	public Engine(){
		ParticleField.createField(5000);
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
		ArrayList<Particle> field = ParticleField.getField();
		for(Particle part : field){
			part.y = Math.sin(part.time) * part.z;
			part.x = Math.cos(part.time) * part.y;
			part.time += 0.01;
		}

		//Render.angle += 0.01;
	}

}
