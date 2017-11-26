package physicsEngine;

import java.awt.Color;
import java.util.Random;

import renderEngine.Render;

public class Particle {
	
	//TEMPORARY PUBLIC DOUBLES - Eventually use operations
	public double x, y, z, time = 1;
	
	private Color color;
	
	/**
	 * Generates a particle with a randomized time. Probably should be changed.
	 * 
	 * @param x Initial x value.
	 * @param y Initial y value.
	 * @param z Initial z value.
	 */
	
	public Particle(double x, double y, double z){
		this.x = x;
		this.y = y;
		this.z = z;
		Random random = new Random();
		//color = new Color(255,255,255);
		time = random.nextDouble()*Math.PI*2;
		/*
		 Setting up particles
		this.y = Math.sin(time)*y;
		this.x = Math.cos(time)*y;
		this.z = Math.sqrt(10000 - Math.pow(x, 2) - Math.pow(y, 2));
		 */
	}
	
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
