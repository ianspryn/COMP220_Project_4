package operations;

public class OperationDivide implements Operation {
	
	Operation a, b;
	
	/**
	 * Operation that divides one operation's value from another.
	 * The operators are in the order b, a in the constructor
	 * due to the Postfix algorithm.
	 * 
	 * @param b Value divided from a
	 * @param a Original value
	 */
	
	public OperationDivide(Operation b, Operation a){
		this.a = a;
		this.b = b;
	}

	@Override
	public double operate() {
		return a.operate() / b.operate();
	}

}
