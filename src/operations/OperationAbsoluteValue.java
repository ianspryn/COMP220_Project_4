package operations;

public class OperationAbsoluteValue implements Operation {

	Operation a;
	
	public OperationAbsoluteValue(Operation a){
		this.a = a;
	}
	
	@Override
	public double operate() {
		return Math.abs(a.operate());
	}
	
}
