package operations;

public class OperationPower implements Operation {

	Operation a, b;
	
	/**
	 * Operation that takes the power of one to another
	 * The operators are in the order b, a in the constructor
	 * due to the Postfix algorithm.
	 * 
	 * @param b Power value
	 * @param a Original value
	 */
	
	public OperationPower(Operation b, Operation a){
		this.a = a;
		this.b = b;
	}

	@Override
	public double operate() {
		return Math.pow(a.operate(),b.operate());
	}
	
}