package renderEngine;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;

import physicsEngine.Engine;
import physicsEngine.Particle;
import physicsEngine.ParticleField;

public class Render {
	
	private static BufferStrategy bs;
	private static Graphics g;
	public static double angle = Math.PI/4;
	
	/**
	 * The main method doing all of the rendering set to 60 FPS.
	 * Called by the ticking method in Display.
	 * 
	 * @param canvas The Display's Canvas.
	 */
	
	public static void mainRender(Canvas canvas){
		bs = canvas.getBufferStrategy();
		if(bs == null){
			canvas.createBufferStrategy(2);
			return;
		}
		g = bs.getDrawGraphics();
		
		g.setColor(new Color(0,0,0));
		g.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());

		render3DGraph(canvas.getWidth(), canvas.getHeight());
		renderParticles(canvas.getWidth()/2, canvas.getHeight()/2);
		
		bs.show();
		g.dispose();
	}
	
	/**
	 * Renders the coordinate axes.
	 * 
	 * @param x Canvas's width
	 * @param y Canvas's height
	 */
	
	public static void render3DGraph(int x, int y){
		int centerx = x/2, centery = y/2;
		
		g.setColor(new Color(255,127,127));
		g.drawLine(centerx+100, centery, centerx-100, centery);
		
		g.setColor(new Color(127,255,127));
		g.drawLine(centerx, centery + 100, centerx, centery - 100);
		
		g.setColor(new Color(127,127,255));
		g.drawLine(centerx - (int)(Math.cos(angle)*100), centery + (int)(Math.cos(angle)*Math.sin(angle)*100), centerx + (int)(Math.cos(angle)*100), centery - (int)(Math.cos(angle)*Math.sin(angle)*100));
	}
	
	/**
	 * Renders the particles.
	 * 
	 * @param x Center of the Canvas's width
	 * @param y Center of the Canvas's height
	 */
	
	public static void renderParticles(int x, int y){
		ArrayList<Particle> field = ParticleField.getField();
		for(Particle part : field){
			g.setColor(part.getColor());
			g.drawRect(part.getX() + x, part.getY() + y, 0, 0);
		}
	}

}
