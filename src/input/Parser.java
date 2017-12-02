package input;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;

import operations.Operation;

public class Parser {
	
	// Will parse an input into operations
	
//	public static void main(String args[]) {
//		Scanner scnr = new Scanner(System.in);
//		String parse = scnr.nextLine();
//		System.out.println(PostFix(parse.toLowerCase().replaceAll("\\s+","")));
//	}
	
	public ArrayList<ArrayList<Operation>> parseString(String parse){
		
		ArrayList<ArrayList<Operation>> operation = new ArrayList<ArrayList<Operation>>();
		
		//Convert from an Inorder expression (Infix) to a Postfix expression (Postfix), convert to lower case, and remove all spaces
		PostFix(parse.toLowerCase().replaceAll("\\s+",""));
		
		Scanner equation = new Scanner(parse);
		equation.useDelimiter("");
		
		if (equation.hasNextDouble()) {
			Double a = equation.nextDouble();
			
		}
		
		
		return EEEEEEEEEEEEEEEEEE; //return an ArrayList
	}
	
	/*public static double eval(final String str) {
	    return new Object() {
	        int pos = -1, ch;

	        void nextChar() {
	            ch = (++pos < str.length()) ? str.charAt(pos) : -1;
	        }

	        boolean eat(int charToEat) {
	            while (ch == ' ') nextChar();
	            if (ch == charToEat) {
	                nextChar();
	                return true;
	            }
	            return false;
	        }

	        double parse() {
	            nextChar();
	            double x = parseExpression();
	            if (pos < str.length()) {
	            	throw new RuntimeException("Unexpected: " + (char)ch);
	            }
	            return x;
	        }

	        // Grammar:
	        // expression = term | expression `+` term | expression `-` term
	        // term = factor | term `*` factor | term `/` factor
	        // factor = `+` factor | `-` factor | `(` expression `)`
	        //        | number | functionName factor | factor `^` factor

	        double parseExpression() {
	            double x = parseTerm();
	            for (;;) {
	                if (eat('+')) {
	                	x += parseTerm(); // addition
	                }
	                else if (eat('-')) {
	                	x -= parseTerm(); // subtraction
	                }
	                else {
	                	return x;
	                }
	            }
	        }

	        double parseTerm() {
	            double x = parseFactor();
	            for (;;) {
	                if (eat('*')) {
	                	x *= parseFactor(); // multiplication
	                }
	                else if (eat('/')) {
	                	x /= parseFactor(); // division
	                }
	                else {
	                	return x;
	                }
	            }
	        }

	        double parseFactor() {
	            if (eat('+')) {
	            	return parseFactor(); // unary plus
	            }
	            if (eat('-')) {
	            	return -parseFactor(); // unary minus
	            }

	            double x;
	            int startPos = this.pos;
	            if (eat('(')) { // parentheses
	                x = parseExpression();
	                eat(')');
	            } else if ((ch >= '0' && ch <= '9') || ch == '.') { // numbers
	                while ((ch >= '0' && ch <= '9') || ch == '.') nextChar();
	                x = Double.parseDouble(str.substring(startPos, this.pos));
	            } else if (ch >= 'a' && ch <= 'z') { // functions
	                while (ch >= 'a' && ch <= 'z') nextChar();
	                String func = str.substring(startPos, this.pos);
	                x = parseFactor();
	                if (func.equals("sqrt")) {
	                	x = Math.sqrt(x);
	                }
	                else if (func.equals("sin")) {
	                	x = Math.sin(Math.toRadians(x));
	                }
	                else if (func.equals("cos")) {
	                	x = Math.cos(Math.toRadians(x));
	                }
	                else if (func.equals("tan")) {
	                	x = Math.tan(Math.toRadians(x));
	                }
	                else {
	                	throw new RuntimeException("Unknown function: " + func);
	                }
	            } else {
	                throw new RuntimeException("Unexpected: " + (char)ch);
	            }

	            if (eat('^')) {
	            	x = Math.pow(x, parseFactor()); // exponentiation
	            }

	            return x;
	        }
	    }.parse();
	}*/
	
	public static String PostFix(String parse) {

	    Stack<Character> stack = new Stack<Character>();
	    StringBuilder postfix = new StringBuilder(parse.length());

	    for (int i = 0; i < parse.length(); i++) {
	    	char c = parse.charAt(i);

	        if (!isOperator(c)) {
	            postfix.append(c);
	        }

	        else {
	            if (c == ')') {
	                while (!stack.isEmpty() && stack.peek() != '(') {
	                    postfix.append(stack.pop());
	                }
	                if (!stack.isEmpty()) {
	                    stack.pop();
	                }
	            }
	            else {
	                if (!stack.isEmpty() && !isLowerPrecedence(c, stack.peek())) {
	                    stack.push(c);
	                }
	                else {
	                    while (!stack.isEmpty() && isLowerPrecedence(c, stack.peek())) {
	                        char pop = stack.pop();
	                        if (c != '(') {
	                            postfix.append(pop);
	                        } else {
	                          c = pop;
	                        }
	                    }
	                    stack.push(c);
	                }
	            }
	        }
	    }
	    while (!stack.isEmpty()) {
	      postfix.append(stack.pop());
	    }
	    return postfix.toString();
	}
	
	private static boolean isOperator(char c) {
	    return c == '+' || c == '-' || c == '*' || c == '/' || c == '^' || c == '(' || c == ')';
	}

	private static boolean isLowerPrecedence(char a, char b) {
	    switch (a) {
	        case '+':
	        case '-':
	            return !(b == '+' || b == '-');
	        case '*':
	        case '/':
	            return b == '^' || b == '(';
	        case '^':
	            return b == '(';
	        case '(':
	            return true;
	        default:
	            return false;
	    }
	}

}
