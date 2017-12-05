package operations;

public class OperationCosine implements Operation {

	Operation a;
	
	/**
	 * Returns the tangent of an operation.
	 * 
	 * @param a Operation having tangent applied.
	 */
	
	public OperationCosine(Operation a){
		this.a = a;
	}
	
	@Override
	public double operate() {
		return Math.cos(a.operate());
	}

}
