package operations;

public class OperationAdd implements Operation {
	
	Operation a, b;
	
	/**
	 * Returns the sum of two operations.
	 * 
	 * @param a Operation
	 * @param b Operation
	 */
	
	public OperationAdd(Operation a, Operation b){
		this.a = a;
		this.b = b;
	}

	@Override
	public double operate() {
		return a.operate() + b.operate();
	}

}
