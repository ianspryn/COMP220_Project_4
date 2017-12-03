package input;

import java.io.IOException;
import java.util.Scanner;
import java.util.Stack;

import operations.Operation;

public class Parser {
	
	// Will parse an input into operations
	
	public static void main(String[] args) throws IOException {
		Scanner scnr = new Scanner(System.in);
		String parse = scnr.nextLine();
		System.out.println(new Parser(parse.toLowerCase().replaceAll("\\s+", "")).doTrans());
	}
	
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
	            default:
	            	for (int j = i; j < parse.length(); j++) {
	            		if (!Character.isDigit(parse.charAt(j))) {
	            			break;
		        		}
		        		output += (parse.charAt(j));
		        		i = j;
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
	
//	public Operation parseString(String parse){
//		
//		
//		
//		//Convert from an Inorder expression (Infix) to a Postfix expression (Postfix), convert to lower case, and remove all spaces
//		new Parser(parse.toLowerCase().replaceAll("\\s+", "")).doTrans();
//		
//		
//		
//		Scanner equation = new Scanner(parse);
//		equation.useDelimiter("");
//		
//		if (equation.hasNextDouble()) {
//			Double a = equation.nextDouble();
//			
//		}
//		
//		
//		return EEEEEEEEEEEEEEEEEE; //return an ArrayList
//	}
	
	

}
