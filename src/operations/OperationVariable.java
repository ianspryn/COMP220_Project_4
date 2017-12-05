package operations;

import physicsEngine.ParticleField;

public class OperationVariable implements Operation {
	
	char variable;
	
	/**
	 * Uses the iterator in ParticleField to access the variable value on the
	 * current particle being manipulated.
	 * 
	 * @param c Variable represented as a char
	 */
	
	public OperationVariable(char c){
		variable = c;
	}
	
	public double operate(){
		if(variable == 'x'){
			return ParticleField.getCurrentParticle().x;
		} else if (variable == 'y'){
			return ParticleField.getCurrentParticle().y;
		} else if (variable == 'z'){
			return ParticleField.getCurrentParticle().z;
		} else 
			return ParticleField.getCurrentParticle().time;
	}

}
