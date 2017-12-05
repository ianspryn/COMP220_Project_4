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
	            	
	            case 'a': //absolute value
	            case 'c': //cos
	            case 'e': //Euler's number
	            case 'p': //pi
	            case 'i': //sin
	            case 'n': //tan
	            case 's': //sqrt
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