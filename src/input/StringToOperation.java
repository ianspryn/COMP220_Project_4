package input;

import java.util.ArrayList;
import java.util.Scanner;

import operations.*;

public class StringToOperation {
	
	public static Operation stringToOperation(String unpros){
		/* PSUEDO
		 * 
		 * Do list of objects, not string
		 * 
		 * Constant X
		 * Constant X
		 * Constant X
		 * Divide - USE PREVIOUS TWO X
		 * Constant X
		 * Constant X
		 * Multiply - USE PREVIOUS TWO X
		 * Add - USE PREVIOUS TWO X
		 * ADD - USE PREVIOUS TWO (RETURN)
		 * 
		 * 
		 */
		ArrayList<Operation> opsLeft = new ArrayList<Operation>();
		
		Scanner scnr = new Scanner(unpros);
		scnr.useDelimiter("");
		while(scnr.hasNext()){
			if(scnr.hasNextDouble()){
				opsLeft.add(new OperationConstant(scnr.nextDouble()));
			} else {
				char op = scnr.next().charAt(0);
				if(op == '+'){
					opsLeft.add(new OperationAdd(opsLeft.get(opsLeft.size()-2), opsLeft.get(opsLeft.size()-1)));
					opsLeft.remove(opsLeft.size() - 2);
					opsLeft.remove(opsLeft.size() - 2);
				} else if(op == '-'){
					opsLeft.add(new OperationSubtract(opsLeft.get(opsLeft.size()-2), opsLeft.get(opsLeft.size()-1)));
					opsLeft.remove(opsLeft.size() - 2);
					opsLeft.remove(opsLeft.size() - 2);
				} else if(op == '*'){
					opsLeft.add(new OperationMultiply(opsLeft.get(opsLeft.size()-2), opsLeft.get(opsLeft.size()-1)));
					opsLeft.remove(opsLeft.size() - 2);
					opsLeft.remove(opsLeft.size() - 2);
				} else if(op == '/'){
					opsLeft.add(new OperationDivide(opsLeft.get(opsLeft.size()-2), opsLeft.get(opsLeft.size()-1)));
					opsLeft.remove(opsLeft.size() - 2);
					opsLeft.remove(opsLeft.size() - 2);
				}
			}
		}
		scnr.close();
		return opsLeft.get(0);
	}

}
