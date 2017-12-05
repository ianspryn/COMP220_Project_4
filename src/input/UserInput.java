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

public class UserInput {
	
	/**
	 * Prompts the user to input information, including the function, a time variable, and the speed of rotation.
	 */
	public static void main(String args[]) throws IOException {

		Scanner scnr = new Scanner(System.in);
		
//		System.out.println("Please enter your function");
		//take the user input as a string, remove all spaces from it, and convert it to an ArrayList of characters
		
		System.out.print(Parser.translate(scnr.nextLine()));
		
	}
	
	

}