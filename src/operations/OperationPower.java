package operations;

public class OperationPower implements Operation {

	Operation a, b;
	
	public OperationPower(Operation a, Operation b){
		this.a = a;
		this.b = b;
	}

	@Override
	public double operate() {
		return Math.pow(a.operate(),b.operate());
	}
	
}