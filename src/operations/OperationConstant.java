package operations;

public class OperationConstant implements Operation {
	
	Double a;
	
	//Maybe have an OperationParticleVariable?
	
	public OperationConstant(Double a){
		this.a = a;
	}
	
	@Override
	public double operate(){
		return a.doubleValue();
	}

}
