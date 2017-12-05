package operations;

public class OperationSubtract implements Operation {
	
	Operation a, b;
	
	/**
	 * Operation that subtracts one operation's value from another.
	 * The operators are in the order b, a in the constructor
	 * due to the Postfix algorithm.
	 * 
	 * @param b Value removed from a
	 * @param a Original value
	 */
	
	public OperationSubtract(Operation b, Operation a){
		this.a = a;
		this.b = b;
	}

	@Override
	public double operate() {
		return a.operate() - b.operate();
	}

}
