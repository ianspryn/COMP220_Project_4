package operations;

public class OperationSine implements Operation {

	Double a;
	
	public OperationSine(double a){
		this.a = a;
	}
	
	@Override
	public double operate() {
		return Math.sin(a);
	}

}
