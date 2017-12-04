package input;

import java.util.Scanner;
import java.util.Stack;

import operations.*;

public class StringToOperation {
	
	public static Operation stringToOperation(String unpros){
		Stack<Operation> opsLeft = new Stack<Operation>();
		
		Scanner scnr = new Scanner(unpros);
		while(scnr.hasNext()){
			if(scnr.hasNextDouble()){
				opsLeft.add(new OperationConstant(scnr.nextDouble()));
			} else {
				char op = scnr.next().charAt(0);
				if(op == '+'){
					opsLeft.add(new OperationAdd(opsLeft.pop(), opsLeft.pop()));
				} else if(op == '-'){
					opsLeft.add(new OperationSubtract(opsLeft.pop(), opsLeft.pop()));
				} else if(op == '*'){
					opsLeft.add(new OperationMultiply(opsLeft.pop(), opsLeft.pop()));
				} else if(op == '/'){
					opsLeft.add(new OperationDivide(opsLeft.pop(), opsLeft.pop()));
				} else if(op == '^'){
					opsLeft.add(new OperationPower(opsLeft.pop(), opsLeft.pop()));
				}
			}
		}
		scnr.close();
		return opsLeft.get(0);
	}

}
