package operations;

public class OperationCosine implements Operation {

	Operation a;
	
	public OperationCosine(Operation a){
		this.a = a;
	}
	
	@Override
	public double operate() {
		return Math.cos(a.operate());
	}

}
