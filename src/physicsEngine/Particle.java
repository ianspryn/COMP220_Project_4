package physicsEngine;

import java.awt.Color;
import java.util.Random;

import renderEngine.Render;

public class Particle {
	
	public double x, y, z, time;
	
	public Particle(){
		time = new Random().nextDouble()*100;
	}
	
	/**
	 * Generates a particle with a randomized time. Probably should be changed.
	 * 
	 * @param x Initial x value.
	 * @param y Initial y value.
	 * @param z Initial z value.
	 */
	
	
	/**
	 * Projects the 3D particle unto 2D for rendering.
	 * 
	 * @return X value for rendering.
	 */
	
	public int getX(){
		return (int)x - (int)(Math.cos(Render.angle)*z);
	}
	
	/**
	 * Projects the 3D particle unto 2D for rendering.
	 * 
	 * @return Y value for rendering.
	 */
	
	public int getY(){
		return -((int)y - (int)(Math.cos(Render.angle)*Math.sin(Render.angle)*z));
	}
	
	/**
	 * Based off the position of the particle on the z axis, changes the color of the particle to give a better sense of 3D.
	 * The more positive, the whiter the particle is.
	 * The more negative, the bluer the particle is.
	 * The particle is cyan at z = 0.
	 * 
	 * @return The color of the particle.
	 */
	
	public Color getColor(){
		return new Color(z > 0 ? 255 - (int)(255/(z/50+1)) : 0, z < 0 ? (int)(255/(-z/50+1)) : 255, 255);
	}

}
