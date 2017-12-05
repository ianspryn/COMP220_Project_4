package operations;

public class OperationAbsoluteValue implements Operation {

	Operation a;
	
	/**
	 * Returns the absolute value of an operation.
	 * 
	 * @param a Operation having sine applied.
	 */
	
	public OperationAbsoluteValue(Operation a){
		this.a = a;
	}
	
	@Override
	public double operate() {
		return Math.abs(a.operate());
	}
	
}
