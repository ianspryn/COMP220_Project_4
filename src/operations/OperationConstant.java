package operations;

public class OperationConstant implements Operation {
	
	double a;
	
	/**
	 * Creates a constant that does not change.
	 * 
	 * @param a Constant value
	 */
	
	public OperationConstant(double a){
		this.a = a;
	}
	
	@Override
	public double operate(){
		return a;
	}

}
