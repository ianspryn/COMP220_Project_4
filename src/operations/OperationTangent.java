package operations;

public class OperationTangent implements Operation {

	Operation a;
	
	/**
	 * Returns the tangent of an operation.
	 * 
	 * @param a Operation having tangent applied.
	 */
	
	public OperationTangent(Operation a){
		this.a = a;
	}
	
	@Override
	public double operate() {
		return Math.tan(a.operate());
	}

}
