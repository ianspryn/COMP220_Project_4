package operations;

public class OperationAdd implements Operation {
	
	Double a, b;
	
	public OperationAdd(Double a, Double b){
		this.a = a;
		this.b = b;
	}

	@Override
	public double operate() {
		return a + b;
	}

}
