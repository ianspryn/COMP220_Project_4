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
		ArrayList<Character> charParse = new ArrayList<Character>(scnr.nextLine().replaceAll("\\s+", "").chars().mapToObj(e -> (char) e).collect(Collectors.toList()));
		//time
		//rotation
		
		//Add parentheses around first operator after trigonometric function
		//Example: sinx2 becomes sin(x)2
		if (charParse.size() > 2) {
			//If it is not greater than 2, then it is guaranteed there is no trigonometric function in the user input
			addTrigParentheses(charParse);
		}
		
		//Convert user input containing absolutely value of something like |x| to a(x)
		convertAbs(charParse);
		
		//Convert pi to p
		convertPi(charParse);
		
		//sin --> i, cos --> c, tan --> a, sqrt --> s, pi --> p
		convertTrig(charParse);
		
		//Add multiples before opening parentheses
		//Example: 2(x) becomes 2*(x)
		//Example: 2tanx becomes 2(tanx)
		if (charParse.size() > 1) {
			//If it is not greater than 1, then it is guaranteed there is no multiples that is applicable
			addMultiple(charParse);
		}
		
		//Check if parentheses are balanced in user input. If not, throw an exception
		if (!isBalanced(charParse)) {
			throw new IllegalArgumentException("Parentheses are not balanced");
		} else {
			//If they are balanced, set the absolute value brackets to parentheses for future parsing
			Collections.replaceAll(charParse, '[', '(');
			Collections.replaceAll(charParse, ']', ')');
		}
		
		//Check if user inputs invalid character(s), includes at least 1 variable, and there are no 2 operands in a row
		//Example: Not allowed: underscore (_), percent (%), 2++x
		//Example: Includes a mathematical variable, like 2+x
		if (!isValidInput(charParse)) {
			throw new IllegalArgumentException("Invalid input");
		}
		
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
	
	public static ArrayList<Character> addTrigParentheses (ArrayList<Character> userText) {
		
		int i = 2;
		while (i < userText.size()) {
			char c = userText.get(i);
			if ((c == 'n' || c == 's') && userText.get(i + 1) != 'q' && userText.get(i + 1) != '(') {
				userText.add(i + 1, '(');
				userText.add(i + 3, ')');
			}
			i++;
		}
		return userText;
	}
	
	/**
	 * Add the multiplication (*) symbol before any opening parentheses, variables, trigonometric functions, and constants for easier parsing
	 * @param userText
	 * @return an ArrayList of type Character
	 */
	public static ArrayList<Character> addMultiple (ArrayList<Character> userText) {
		
		int i = 1;
		//We set this equal to 1 instead of 0 because if the user inputs something like "(1)" we are not interested
		//in adding a * symbol at the beginning
		while (i < userText.size()) {
			char c = userText.get(i);
			//If it is a variable, or first letter of sin, cos, tan, sqrt, pi, or e
			if(c == '(' || isVariable(c) || c == 'i' || c == 'c' || c == 'n' || c == 's' || c == 'p' || c == 'e') {
				//Make sure the character before the current position is a number or a letter and that it is not a trigonometric function or a square root function
				if ((Character.isDigit(userText.get(i - 1)) || Character.isAlphabetic(userText.get(i - 1)))
						&& userText.get(i - 1) != 'i' && userText.get(i - 1) != 'c' && userText.get(i - 1) != 'n' && userText.get(i - 1) != 'a' && userText.get(i - 1) != 's') {
					userText.add(i, '*'); 
					i++;	
				}
			}
			//if it is a variable, a digit, or first letter of sin, cos, tan, absolute value, pi, or e
			if ((isVariable(c) || Character.isDigit(c) || c == 'i' || c == 'c' || c == 'n' || c == 'a' || c== 'p' || c == 'e')
					&& (userText.get(i - 1) == ')' || userText.get(i - 1) == 'i' || userText.get(i - 1) == 'e')) {
				userText.add(i, '*');
				i++;
			}
			//if it is digit and the position before is a variable, a pi constant, or an e constant, add a multiplication symbol between
			if (Character.isDigit(c) && (isVariable(userText.get(i - 1)) || userText.get(i - 1) == 'p' || userText.get(i - 1) == 'e')) {
				userText.add(i, '*');
				i++;
			}
			i++;
		}
		return userText;
	}
	
	/**
	 * Convert absolute value symbols into a more easily parsable format
	 * Example: |x| becomes a[x]
	 * Example: |sin(x)| becomes a[sin(x)]
	 * Note: brackets are used instead of parentheses in order to use isBalanced(). If they were sent to parentheses, (|)| would
	 * return true even though it should be false.
	 * @param userText
	 * @return an ArrayList with the newly formatted absolute value
	 */
	public static ArrayList<Character> convertAbs (ArrayList<Character> userText) {
		if (userText.contains('|')) {
			return userText;
		}
		int isOpeningBar = -1;
		int i = 0;
		while (i < userText.size()) {
			char c = userText.get(i);
			if (c == '|') {
				isOpeningBar *= -1;
				if (isOpeningBar == 1) {
					userText.set(i, 'a');
					userText.add(i, '[');
				} else {
					userText.set(i, ']');
				}
			}
			i++;
		}
		return userText;
	}

	/**
	 * Converts pi to a single variable of p
	 * @param userText
	 * @return an ArrayList with only p for pi
	 */
	public static ArrayList<Character> convertPi (ArrayList<Character> userText) {
		int i = 0;
		while (i < userText.size()) {
			char c = userText.get(i);
			if (userText.get(i) == 'p') {
				try {
					if (userText.get(i + 1) == 'i') {
						userText.remove(i + 1);
					} else {
						throw new IllegalArgumentException("user probably misstyped \"pi\" or treated \"p\" as a variable");
					}
				} catch (StringIndexOutOfBoundsException e) {
					throw new StringIndexOutOfBoundsException("User entered invalid variable character");
				}
			}
			i++;
		}
		return userText;
	}
	
	/**
	 * Converts trigonometric functions into single-letter functions
	 * Example: sin --> i
	 * Example: cos --> c
	 * @param userText
	 * @return an ArrayList of single-letter trigonometric functions
	 */
	public static ArrayList<Character> convertTrig (ArrayList<Character> userText) {
		int i = 0;
		while (i < userText.size()) {
			char c = userText.get(i);
			//Potentially a sine or sqrt function
	       if (c == 's') {
	        	/*
	        	 * The try catch statement is a safe guard so that if the user enters the character 's' and intended
	        	 * it to be a variable, the program will still fail gracefully
	        	 */	 
	        	try {
	        		if (userText.get(i + 1) == 'i' && userText.get(i + 2) == 'n') {
	        			//remove the 's' and 'n' in "sin"
	        			userText.remove(i);
	        			userText.remove(i+1);
	        		} else if (userText.get(i + 1) == 'q' && userText.get(i + 2) == 'r' && userText.get(i + 3) == 't') {
	        			//remove the "qrt" in "sqrt"
	        			userText.remove(i+1);
	        			userText.remove(i+1);
	        			userText.remove(i+1);
	        		} else {
	        			throw new IllegalArgumentException("user probably misstyped \"sin\" or \"sqrt\" or treated \"s\" as a variable");
	        		}
	        	} catch (StringIndexOutOfBoundsException e) {
	        		throw new StringIndexOutOfBoundsException("User entered invalid variable character");
	        		}
			}
	        //Potentially a cosine function
	        if (c == 'c') {
	        	/*
	        	 * The try catch statement is a safe guard so that if the user enters the character 'c' and intended
	        	 * it to be a variable, the program will still fail gracefully
	        	 */	 
	        	try {
	        		if (userText.get(i + 1) == 'o' && userText.get(i + 2) == 's') {
	        			//remove the "os" in "cos"
	        			userText.remove(i + 1);
	        			userText.remove(i + 1);
	        		} else {
	        			throw new IllegalArgumentException("user probably misstyped \"cos\" or treated \"c\" as a variable");
	        		}
	        	} catch (StringIndexOutOfBoundsException e) {
	        		throw new StringIndexOutOfBoundsException("User entered invalid variable character");
	        		}
	        		
	        }
	        	
	        //Potentially a tangent function
	        if (c == 't') {
	        	/*
	        	 * The try catch statement is a safe guard so that if the user enters the character 't' and intended
	        	 * it to be a variable, the program will still fail gracefully
	        	 */	            	
	        	try {
	        		if (userText.get(i + 1) == 'a' && userText.get(i + 2) == 'n') {
	        			//remove the "an" in "tan"
	        			userText.remove(i);
	        			userText.remove(i);
	        		} else {
	        			throw new IllegalArgumentException("user probably misstyped \"tan\" or treated \"t\" as a variable");
	        		}
	        	} catch (StringIndexOutOfBoundsException e) {
	        		throw new StringIndexOutOfBoundsException("User entered invalid variable character");
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
			} else if (c == '[') {
				stack.push(c);
			} else if (c == ')') {
				if (stack.isEmpty()) {
					return false;
				} else if (stack.peek() == '(') {
					stack.pop();
				} else {
					return false;
				}
			} else if (c == ']') {
				if (stack.isEmpty()) {
					return false;
				} else if (stack.peek() == '[') {
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
	 * Check if a character is a character supported,in a function, which includes a - z, 0 - 9, operands, decimals, and vertical bars.
	 * @param c
	 * @return true or false if a character is a function character
	 */
	public static boolean isFunctionCharacter (char c) {
		return (Character.isDigit(c) || Character.isAlphabetic(c) || isOperand(c)) || c == '.' || c == '|';
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