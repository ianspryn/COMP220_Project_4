package operations;

public class OperationDivide implements Operation {
	
	Operation a, b;
	
	public OperationDivide(Operation b, Operation a){
		this.a = a;
		this.b = b;
	}

	@Override
	public double operate() {
		return a.operate() / b.operate();
	}

}
