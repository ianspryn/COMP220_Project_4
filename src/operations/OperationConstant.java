package operations;

public class OperationConstant {
	
	Double a;
	
	public OperationConstant(Double a){
		this.a = a;
	}
	
	public double operate(){
		return a.doubleValue();
	}

}
