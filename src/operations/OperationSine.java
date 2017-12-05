package operations;

public class OperationSine implements Operation {

	Operation a;
	
	/**
	 * Returns the sine of an operation.
	 * 
	 * @param a Operation having sine applied.
	 */
	
	public OperationSine(Operation a){
		this.a = a;
	}
	
	@Override
	public double operate() {
		return Math.sin(a.operate());
	}

}
