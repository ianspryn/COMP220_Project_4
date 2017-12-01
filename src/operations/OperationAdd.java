package operations;

public class OperationAdd implements Operation {
	
	Operation a, b;
	
	public OperationAdd(Operation a, Operation b){
		this.a = a;
		this.b = b;
	}

	@Override
	public double operate() {
		return a.operate() + b.operate();
	}

}
