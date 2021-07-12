package bet;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;



/**
 * @author Charles Kann
 *
 * October 1, 2002
 *
 * This project creates a Binary Expression Tree from an infix equation
 * Revision : Luis Pena
 * Date     : July 6th 2021
 * Purpose  : BET Party Palooza
 */

public class BinaryExpressionTree {

    public static void main(String args[]) {
        try {

            // Set up the Buffered reader to read a line.
            System.out.println("Enter Expression To Calculate");
            BufferedReader br =
                new BufferedReader(new InputStreamReader(System.in));

            // call evaluateExpression to evaluate this expression and print the answer.
			Node root = evaluateExpression(br.readLine());
			System.out.println("PreOder: " + root.preOrder());
			System.out.println("InOder: "+ root.inOrder());
			System.out.println("Postorder: "+ root.postOrder());
			System.out.println("Calculating: " + root.calculate());

		} catch(Exception e) {
            e.printStackTrace();
        }
    }

    public static Node evaluateExpression(String expression)
            throws InvalidOperatorException {
        Stack<Node> operands = new ArrayListStack<Node>();
		Stack<Operator> operators = new ArrayListStack<Operator>();

		// String to keep track of whats inside the tree. For debugging.
		StringBuilder mNode = new StringBuilder();

        StringTokenizer st = new StringTokenizer(expression, "+-/*() \t", true);

        // Process each token
        while (st.hasMoreTokens()) {
            String token = st.nextToken();
			System.out.println("Current Token: " + token);

			// Throw out blank spaces
			if (token.equals(" ") || token.equals("\t")){
				System.out.println("Space: " + token);
			}

			// If its an operand, create an operandNode and push onto the stack
			else if (!Operator.isOperator(token)){ //If token is an operand put it on string
				System.out.println("Creating OperandNode and Pushing:  " + token);

				Node currentNode = new OperandNode(token);
				operands.push(currentNode);
			}

            /* Checking if operator */

			// if start brace push on operators
			else if(Operator.getOperator(token).isStartBrace()){
				System.out.println("Pushing Start Brace:  " + token);
				operators.push(Operator.getOperator(token));
			}

			// else if end brace, pop operators until matching start brace
			else if (Operator.getOperator(token).isEndBrace()){
				System.out.println("Operator Is End Brace: " + token);

				// and the top of stack is not the matching start brace.
				while (!operators.peek().isStartBrace())
				{
					// Pop operators until matching start brace

					// so pop an operator (-) and then pop two nodes (numbers such as 5 & 6) and add the operator as the parent node
					// and two operands as the child nodes.

					String y = operands.peek().toString();
					Node pop1 = operands.pop();

					String z = operands.peek().toString();
					Node pop2 = operands.pop();
					System.out.println("(isEndBrace): Popping off top two operands ... : " + pop2 +  " " +  pop1);
					mNode.append("   ").append(operators.peek().toString()).append(z).append(y);
					OperatorNode mOperatorNode = new OperatorNode(operators.peek(),pop2,pop1);

					// pop Operator and two OperandNode, use to create OperatorNode
					System.out.println("(isEndBrace): Popping off old operator: " + operators.peek());
					operators.pop();

					// push OperatorNode on operand stack.
					System.out.println("(isEndBrace): Creating New OperatorNode From mOperatorNode " + mOperatorNode.stringify());
					operands.push(mOperatorNode);
				}


				System.out.println("(isEndBrace): Popping Start brace: " + operators.peek());
				operators.pop(); //otherwise it is a start brace and pop this off . don't do anything else
			} // End end brace logic




			// else pop until operator on stack is <= operator to push.
			else{
					// pop Operator and two OperandNodes, use to create OperatorNode
					// push OperatorNode on operand stack.

				while (!operators.isEmpty() && operators.peek().getPrecedenceLevel() >= Operator.getOperator(token).getPrecedenceLevel()){

					String y = operands.peek().toString();
					Node pop1 = operands.pop();

					String z = operands.peek().toString();
					Node pop2 = operands.pop();
					System.out.println("(Get Precedence Level): Popping off top two operands ... : " + pop2 +  " " +  pop1);
					mNode.append("   ").append(operators.peek()).append(z).append(y);

					OperatorNode mOperatorNode = new OperatorNode(operators.peek(),pop2,pop1);

					// pop Operator and two OperandNode, use to create OperatorNode
					System.out.println("(Get Precedence Level): Popping off old operator: " + operators.peek());
					operators.pop();

					// push OperatorNode on operand stack.
					System.out.println("(Get Precedence Level):  Creating New OperatorNode From mOperatorNode " + mOperatorNode.stringify());
					operands.push(mOperatorNode);
				} //end while

				if (operators.isEmpty()){

					System.out.println("operators.isEmpty(): Pushing Operator On empty:  " + token);
					operators.push(Operator.getOperator(token));
				}

				// else if operators is empty, just push
				else if (!operators.isEmpty()){

					System.out.println("operators.isNotEmpty(): Pushing Operator with Existing Operators:  " + token);
					operators.push(Operator.getOperator(token));
				}
			} //end else



			System.out.println("Current Token End: " + token);
			System.out.println("The Operators in stack: " + operators.stringify());
			System.out.println("The Operands in stack: " + operands.stringify() + "-----> Current Node is: " + mNode );
			System.out.println("------------------- End Loop -------------------");

            } // end while

		// Pop any remaining input
			// pop OperatorNode and two OperandNodes, and push create OperandNode.
			// push OperandNode on operand stack.
		while(!operators.isEmpty()){ //Finished with tokens and still some left on stack, pop remaining operators
			String y = operands.peek().toString();
			Node pop1 = operands.pop();

			String z = operands.peek().toString();
			Node pop2 = operands.pop();
			System.out.println("(operators.isEmpty):  Popping off top two operands ... : " + pop2 +  " " +  pop1);
			mNode.append("   ").append(operators.peek()).append(z).append(y);


			OperatorNode mOperatorNode = new OperatorNode(operators.peek(),pop2,pop1);

			// pop Operator and two OperandNode, use to create OperatorNode
			System.out.println("(operators.isEmpty): Popping off old operator: " + operators.peek());
			operators.pop();

			// push OperatorNode on operand stack.
			System.out.println("(operators.isEmpty): Creating New OperatorNode From mOperatorNode " + mOperatorNode.stringify());
			operands.push(mOperatorNode);
		}

	        // return the result, the only thing left on the operands stack.
			System.out.println("Returning Results");
        	System.out.println(mNode);
            return operands.pop();
        }

	private static  interface Node  extends Comparable<Node> {
		public String preOrder();
		public String inOrder();
		public String postOrder();
		public double calculate();

	}

	private static class OperandNode implements Node {
    	// can make this a double if I want.
	    private String operand;

	    public OperandNode(String operand) {
	        this.operand = operand;
	    }

		public double calculate() {
			// You need to fill this method in.
			return Double.parseDouble(operand);
		}

		public String preOrder() {
		    return operand;
		}

		public String inOrder() {
		    return operand;
		}

		public String postOrder() {
		    return operand;
		}

		public String toString() {
	      return operand;
	    }

		public int compareTo(Node n) {
			return (n.toString().compareTo(this.toString()));
		}
	}

	private static class OperatorNode implements Node {
	    Node left, right;
	    Operator operator;

	   public OperatorNode(Operator operator, Node left, Node right)  {
	        this.operator = operator;
	        this.left = left;
	        this.right = right;
	    }

		public double calculate() { return (operator.doCalc(left.calculate(), right.calculate())); }
		public String preOrder() {
		    return(" " + operator.toString() + " ROOTN,   " +
		           left.preOrder() + " LN,   " +
		    	   right.preOrder() + " RN,   " );
		}

		public String inOrder() {
		    return("(" + left.inOrder() + " LN,   " +
		    	   operator.toString() + " ROOTN,   "  +
		    	   right.inOrder() + ")" + " RN,   " );
		}

		public String postOrder() {
		    return(" " + left.postOrder() + " LN,   " +
		          right.postOrder() + " RN,   " +
		    	  operator.toString() + " ROOTN,   ");
		}

		public String stringify() {
			return(" " + left.toString() + " " +
					right.toString() + " " +
					operator.toString() + " ");
		}

		@Override
		public int compareTo(Node n) {
			// TODO Auto-generated method stub
			return (this.toString().compareTo(n.toString()));
		}
	}
}


