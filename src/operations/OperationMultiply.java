package operations;

public class OperationMultiply implements Operation {
	
	Operation a, b;
	
	public OperationMultiply(Operation a, Operation b){
		this.a = a;
		this.b = b;
	}

	@Override
	public double operate() {
		return a.operate() * b.operate();
	}

}
