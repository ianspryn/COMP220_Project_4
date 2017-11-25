package operations;

public interface Operation {
	
	// There will be many operations, each doing an individual operation and sometimes holding a reference to a variable
	
	abstract double operate(double a, double b);

}
