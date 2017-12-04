package operations;

public class OperationSquareRoot implements Operation {

	Operation a;
	
	public OperationSquareRoot(Operation a){
		this.a = a;
	}
	
	@Override
	public double operate() {
		return Math.sqrt(a.operate());
	}

}
