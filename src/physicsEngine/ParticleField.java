package physicsEngine;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public class ParticleField {
	
	private static ArrayList<Particle> particles;
	private static Iterator<Particle> partIter;
	private static Particle currentParticle;
	
	/**
	 * Creates the ArrayList of particles.
	 * 
	 * @param numberOfParticles Number of particles rendered in the field
	 */
	
	public static void createField(int numberOfParticles){
		particles = new ArrayList<Particle>();
		for(int i = 0; i < numberOfParticles; i++){
			particles.add(new Particle());
		}
		partIter = particles.iterator();
		
	}
	
	/**
	 * 	 @return Current particle the class iterator is at.
	 */
	
	public static Particle getCurrentParticle(){
		return currentParticle;
	}
	
	public static boolean cycleAndIfHasNext(){
		if(partIter.hasNext()){
			currentParticle = partIter.next();
			return true;
		} else {
			partIter = particles.iterator();
			return false;
		}
	}
	
	public static ArrayList<Particle> getField(){
		return particles;
	}

}
