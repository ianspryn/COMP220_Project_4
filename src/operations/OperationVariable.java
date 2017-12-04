package operations;

import physicsEngine.ParticleField;

public class OperationVariable implements Operation {
	
	char variable;
	
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
