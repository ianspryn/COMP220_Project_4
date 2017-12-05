package input;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.util.Stack;
import java.util.stream.Collectors;

import operations.Operation;
import physicsEngine.Engine;
import renderEngine.Display;
import renderEngine.Render;

public class UserInput {
	
	/**
	 * Prompts the user to input information, including the function, a time variable, and the speed of rotation.
	 */
	public static void main(String args[]) throws IOException {

		Scanner scnr = new Scanner(System.in);
		
		System.out.println("Would you like to rotate the graph as it is rendered? (yes/no)");
		
		String input = scnr.nextLine().toLowerCase();
		if(input.equals("yes") || input.equals("y")){
			Render.rotateGraph = true;
		}
		
		System.out.println("Enter time increment.");
		Engine.timeIncrement = scnr.nextDouble();
		scnr.nextLine();
		
		System.out.println("Enter the x. Available variables: time (expressed as 't'), x. \nExamples:\n\tt * sin(x)\n\t2x + cos(tan(x))");
		Engine.x = Parser.translate(scnr.nextLine());
		System.out.println("Enter the y. Available variables: time (expressed as 't'), y, x. \nExamples:\n\tt*sin(y)\n\t2y + cos(tan(pi*y))");
		Engine.y = Parser.translate(scnr.nextLine());
		System.out.println("Enter the z. Available variables: time (expressed as 't'), z, y, x. \nExamples:\n\tsin(z)\n\t2z + cos(tan(2pi/z))");
		Engine.z = Parser.translate(scnr.nextLine());
		
		System.out.println("How many particles would you like to simulate?");
		
		int number = scnr.nextInt();
		
		scnr.close();
		
		Thread engine = new Thread(new Engine(number));
		engine.start();
		Thread render = new Thread(new Display());
		render.start();
		
	}
	

	//OOPS: take the user input as a string, remove all spaces from it, and convert it to an ArrayList of characters

}