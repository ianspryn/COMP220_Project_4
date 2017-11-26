package physicsEngine;

import java.util.ArrayList;
import java.util.Random;

public class ParticleField {
	
	// This is almost a stub class, it currently only contains the particle field and generates a new one when the program is started.
	// Hence, this class may be unnecessary.
	
	private static ArrayList<Particle> particles = new ArrayList<Particle>();
	
	public static void createField(int number){
		Random random = new Random();
		for(int i = 0; i < number; i++){
			particles.add(new Particle(random.nextDouble()*100,random.nextDouble()*100,50-random.nextDouble()*100));
		}
	}
	
	public static ArrayList<Particle> getField(){
		return particles;
	}

}
