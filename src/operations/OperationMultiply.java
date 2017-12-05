package operations;

public class OperationMultiply implements Operation {
	
	Operation a, b;
	
	/**
	 * Returns the product of two operations.
	 * 
	 * @param a Operation
	 * @param b Operation
	 */
	
	public OperationMultiply(Operation a, Operation b){
		this.a = a;
		this.b = b;
	}

	@Override
	public double operate() {
		return a.operate() * b.operate();
	}

}
