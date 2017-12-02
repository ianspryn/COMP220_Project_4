package operations;

public class OperationSubtract implements Operation {
	
	Operation a, b;
	
	public OperationSubtract(Operation a, Operation b){
		this.a = a;
		this.b = b;
	}

	@Override
	public double operate() {
		return a.operate() - b.operate();
	}

}
