package operations;

import java.util.Random;

public class OperationRandom implements Operation {
	
	double chosen;
	
	public OperationRandom(double a, double b){
		chosen = a + ((b-a)*new Random().nextDouble());
	}
	
	public double operate(){
		return chosen;
	}

}
