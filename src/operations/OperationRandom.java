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
	
	public OperationRandom(double a, double b){
		chosen = a + ((b-a)*new Random().nextDouble());
	}
	
	public double operate(){
		return chosen;
	}

}
