package physicsEngine;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public class ParticleField {
	
	private static ArrayList<Particle> particles;
	private static Iterator<Particle> partIter;
	private static Particle currentParticle;
	
	//CURRENTLY ASSUMING THERE ARE ALWAYS PARTICLES
	
	public static void createField(int numberOfParticles){
		Random random = new Random();
		particles = new ArrayList<Particle>();
		for(int i = 0; i < numberOfParticles; i++){
			particles.add(new Particle(random.nextDouble()*100,random.nextDouble()*100,50-random.nextDouble()*100));
		}
		partIter = particles.iterator();
		
	}
	
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
