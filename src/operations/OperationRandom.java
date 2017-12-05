package operations;

import java.util.Random;

public class OperationRandom implements Operation {
	
	double chosen;
	
	/**
	 * Creates a random number within the parameters that does not change.
	 * 
	 * @param a Minimum random value
	 * @param b Maximum random value
	 */
	
	public OperationRandom(Operation a, Operation b){
		chosen = a.operate() + ((b.operate()-a.operate())*new Random().nextDouble());
	}
	
	public double operate(){
		return chosen;
	}

}
