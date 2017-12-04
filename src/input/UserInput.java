package input;

import java.io.IOException;
import java.util.ArrayList;
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
		
		//take the user input as a string, remove all spaces from it, and convert it to an ArrayList of characters
		ArrayList<Character> charParse = new ArrayList<Character>(scnr.nextLine().replaceAll("\\s+", "").chars().mapToObj(e -> (char) e).collect(Collectors.toList()));
		//time
		//rotation
//		Scanner scnr = new Scanner(System.in);
//		
//		System.out.println("Please enter your function");
		
		//Check if parentheses are balanced in user input. If not, throw an exception
				if (!isBalanced(charParse)) {
					throw new IllegalArgumentException("Parentheses are not balanced");
				}
				
				//Check if user inputs invalid character(s), includes at least 1 variable, and there are no 2 operands in a row
				//Example: Not allowed: underscore (_), percent (%), 2++x
				//Example: INcludes a mathematical variable, like 2+x
				if (!isValidInput(charParse)) {
					throw new IllegalArgumentException("Invalid input");
				}
				
				//Add multiples before opening parentheses
				//Example: 2(x) becomes 2*(x)
				//Example: 2tanx becomes 2(tanx)
				addMultiple(charParse);
		
				//Rebuild ArrayList of characters back into a string
				StringBuilder sb = new StringBuilder();
				for (Character s : charParse) {
				    sb.append(s);
				}		
				String parsed = new Parser(sb.toString().toLowerCase()).doTrans();
				System.out.println(parsed);
				Operation op = StringToOperation.stringToOperation(parsed);
				System.out.println(op.operate());
		
	}
	
	/**
	 * Add the multiplication (*) symbol before any opening parentheses for easier parsing
	 * @param userText
	 * @return an ArrayList of type Character
	 */
	public static ArrayList<Character> addMultiple (ArrayList<Character> userText) {
		
		int i = 1;
		//We set this equal to 1 instead of 0 because if the user inputs something like "(1)" we are not interested
		//in adding a * symbol at the beginning
		while (i < userText.size()) {
			char c = userText.get(i);
			if(c == '(' || isVariable(c) || c == 's' || c == 'c' || c == 't') {
				if (Character.isDigit(userText.get(i - 1)) || Character.isAlphabetic(userText.get(i - 1))) {
					userText.add(i, '*'); 
					i++;	
				}
			}
			i++;
		}
		return userText;
	}
	
	/**
	 * Checks if the function contains a balanced set of parentheses
	 * @param userText
	 * @return true or false of the parentheses are balanced
	 * @throws IOException
	 */
	public static boolean isBalanced(ArrayList<Character> userText) throws IOException {
		
		Stack<Character> stack = new Stack<Character>();
		
		char c;
		for (int i = 0; i < userText.size(); i++) {
			c = userText.get(i);
			
			if (c == '(') {
				stack.push(c);
			} else if (c == ')') {
				if (stack.isEmpty()) {
					return false;
				} else if (stack.peek() == '(') {
					stack.pop();
				} else {
					return false;
				}
			}
		}
		return stack.isEmpty();
	}
	
	/**
	 * Checks if an ArrayList of Characters contains anything besides numbers, letters, '(', ')', '+', '-', '*', or '/'
	 * Checks if ArrayList contains at least 1 variable (x, y, or z)
	 * @param userText
	 * @return true or false if userText is valid input
	 */
	public static boolean isValidInput(ArrayList<Character> userText) {
		boolean isValid = true;
		boolean containsVariable = false;
		boolean balancedOperand = true;
		boolean decimalSurrounded = true;
		for (int i = 0; i < userText.size(); i++) {
			char c = userText.get(i);
			
			//Verifies userText is valid type of input
			if (!isFunctionCharacter(c)) {
				isValid = false;
				break;
			}
			//Verifies that at least 1 variables exists in the function 
			if (isVariable(c)) {
				containsVariable = true;
			}
			//Checks if there is 2 operands in a row, EXCLUDING parentheses and of operand was not surrounded with operators
			if (isOperandNotParenthesis(c)) {
				try {
					if (isOperandNotParenthesis(userText.get(i - 1)) || (isOperandNotParenthesis(userText.get(i + 1)))) {
						balancedOperand = false;
					}
				}  catch (IndexOutOfBoundsException e) {
            		throw new IndexOutOfBoundsException("Did not surround operand with operators");
            		//If this code is reached, the user ended the function with an operand (excluding parentheses).
            		// Example: 2+ or ^x
            		}
			}
			
			//Check if decimal is followed by digit
			try {
				if (c == '.') {
					decimalSurrounded = Character.isDigit(userText.get(i + 1)); 
				}
			} catch (IndexOutOfBoundsException e) {
        		throw new IndexOutOfBoundsException("Did not balance decimal with operators");
        		//If this code is reached, the user ended the function with a decimal
        		//Example: x + 2.
			}
		}
		return isValid && containsVariable && balancedOperand && decimalSurrounded;
	}
	
	/**
	 * Check if a character is a character supported,in a function, which includes a - z, 0 - 9, operands, and decimals.
	 * @param c
	 * @return true or false if a character is a function character
	 */
	public static boolean isFunctionCharacter (char c) {
		return (Character.isDigit(c) || Character.isAlphabetic(c) || isOperand(c)) || c == '.';
	}
	
	/**
	 * Checks if character is a mathematical variable of either x, y, or z
	 * @param c
	 * @return true or false if character is a variable
	 */
	public static boolean isVariable (char c) {
		return c == 'x' || c == 'y' || c == 'z';
	}
	
	/**
	 * Checks if a char is an operand EXCLUDING parentheses
	 * @param c
	 * @return true or false if a character is an operand
	 */
	public static boolean isOperandNotParenthesis (char c) {
		return c == '+' || c == '-' || c == '*' || c == '/' || c == '^';
	}
	
	/**
	 * Checks if a char is an operand.
	 * @param c
	 * @return true or false if a character is an operand
	 */
	public static boolean isOperand (char c) {
		return c == '+' || c == '-' || c == '*' || c == '/' || c == '^' || c == '(' || c == ')';
	}

}
