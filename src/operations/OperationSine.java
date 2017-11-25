package operations;

public class OperationSine implements Operation {

	@Override
	public double operate(double a, double b) {
		return Math.sin(a);
	}

}
