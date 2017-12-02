import operations.OperationAdd;
import operations.OperationConstant;
import physicsEngine.Engine;
import physicsEngine.ParticleField;
import renderEngine.Display;

public class Main {
	
	/**
	 * The main thread starts the engine thread first, then the rendering thread.
	 * 
	 * @param args Does not affect anything.
	 */
	
	public static void main(String args[]){
		Thread engine = new Thread(new Engine());
		engine.start();
		Thread render = new Thread(new Display());
		render.start();
	}

}
