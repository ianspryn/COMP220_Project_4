package operations;

public class OperationTangent implements Operation {

	Operation a;
	
	public OperationTangent(Operation a){
		this.a = a;
	}
	
	@Override
	public double operate() {
		return Math.tan(a.operate());
	}

}
