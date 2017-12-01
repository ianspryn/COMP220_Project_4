package operations;

public class OperationSine implements Operation {

	Operation a;
	
	public OperationSine(Operation a){
		this.a = a;
	}
	
	@Override
	public double operate() {
		return Math.sin(a.operate());
	}

}
