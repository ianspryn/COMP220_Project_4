package input;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;
import java.util.stream.Collectors;

import operations.Operation;

public class Parser {
	
	// Will parse an input into operations

	private Stack<Character> stack;
	private String parse;
	private String output = "";
	   
	public Parser(String parse) {
		this.parse = parse;
		stack = new Stack<Character>();
	}
	   
	public String doTrans() {
		for (int i = 0; i < parse.length(); i++) {
			char c = parse.charAt(i);
			switch (c) {
				case '+':
				case '-':
					gotOper(c, 1);
					break;
	            
				case '*':
	            case '/':
	            case '^':
	            	gotOper(c, 2);
	            	break;
	            
	            case '(':
	            	stack.push(c);
	            	break;
	           
	            case ')':
	            	gotParen();
	            	break;
	            
	            //Potentially a sine function
	            case 's':
	            	/*
	            	 * The try catch statement is a safe guard so that if the user enters the character 's' and intended
	            	 * it to be a variable, the program will still fail gracefully
	            	 */	 
	            	try {
	            		if (parse.charAt(i + 1) == 'i' && parse.charAt(i + 2) == 'n') {
	            			output += "s ";
	            			i += 3;
	            		} else {
	            			throw new IllegalArgumentException("user probably misstyped \"sin\" or treated \"s\" as a variable");
	            		}
	            	} catch (StringIndexOutOfBoundsException e) {
	            		throw new StringIndexOutOfBoundsException("User entered invalid variable character");
	            		}
	            	break;
	            
	            //Potentially a cosine function
	            case 'c':
	            	/*
	            	 * The try catch statement is a safe guard so that if the user enters the character 'c' and intended
	            	 * it to be a variable, the program will still fail gracefully
	            	 */	 
	            	try {
	            		if (parse.charAt(i + 1) == 'o' && parse.charAt(i + 2) == 's') {
	            			output += "c ";
	            			i += 3;
	            		} else {
	            			throw new IllegalArgumentException("user probably misstyped \"cos\" or treated \"c\" as a variable");
	            		}
	            	} catch (StringIndexOutOfBoundsException e) {
	            		throw new StringIndexOutOfBoundsException("User entered invalid variable character");
	            		}
	            		
	            	break;
	            	
	            //Potentially a tangent function
	            case 't':
	            	/*
	            	 * The try catch statement is a safe guard so that if the user enters the character 't' and intended
	            	 * it to be a variable, the program will still fail gracefully
	            	 */	            	
	            	try {
	            		if (parse.charAt(i + 1) == 'a' && parse.charAt(i + 2) == 'n') {
	            			output += "a ";
	            			i += 3;
	            		} else {
	            			throw new IllegalArgumentException("user probably misstyped \"tan\" or treated \"t\" as a variable");
	            		}
	            	} catch (StringIndexOutOfBoundsException e) {
	            		throw new StringIndexOutOfBoundsException("User entered invalid variable character");
	            		}
	            	break;
	            	
	            case 'x':
	            case 'y':
	            case 'z':
	            	output += c + " ";
	            	break;
	            
	            case '.':
	            	output += c;
	            	break;
	            
	            default:
	            	if (Character.isDigit(parse.charAt(i))) {
	            		for (int j = i; j < parse.length(); j++) {
		            		if (!Character.isDigit(parse.charAt(j)) && parse.charAt(j) != '.') {
		            			break;
			        		}
		            		output += (parse.charAt(j));
		            		i = j;
		            	}
	            	} else {
	            		throw new IllegalArgumentException("User entered an unsupported variable.");
	            	}
	            	output += " ";
	            	break;
			}
		}
		
		while (!stack.isEmpty()) {
			if (stack.peek() != '(') {
				output += stack.pop() + " ";
			} else {
				stack.pop();
			}
		}
		return output;
	}
	   
	public void gotOper(char opThis, int prec1) {
		while (!stack.isEmpty()) {
			if (stack.peek() != '(') {
				char c = stack.pop();
				if ((c == '+' || c == '-') && prec1 == 2) {
					stack.push(c);
					break;
				} else {
					output += c + " ";
				}
			} else {
				break;
			}
		}
		stack.push(opThis);
	}
	   
	public void gotParen() {
		while (!stack.isEmpty()) {
			if (stack.peek() != '('){
				output += stack.pop() + " ";
			} else {
				stack.pop();
				break;
			}
		}
	}
}
