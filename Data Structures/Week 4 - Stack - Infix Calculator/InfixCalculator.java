package com.infix;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * @author Charles Kann
 *
 * October 1, 2002
 *
 * This project implements a simple statck calculator.
 * Revision : Luis Pena
 * Date     : June 26th 2021
 * Purpose  : Infix Calculator
 *
 */
public class InfixCalculator {

    public static void main(String args[]) {
        try {

            // Set up the Buffered reader to read a line.
            System.out.println("Enter Expression To Calculate");
            BufferedReader br =
                new BufferedReader(new InputStreamReader(System.in));

            // call evaluateExpression to evaluate this expression and print the answer.
            System.out.println("ans = " + (evaluateExpression(br.readLine())));
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public static double evaluateExpression(String expression)
            throws InvalidOperatorException {

		// Declare an operators and operand stack.
        Stack<Operator> operators = new ArrayListStack<Operator>();
        Stack<Double> operands = new ArrayListStack<Double>();

        // Get token from input
        StringTokenizer st = new StringTokenizer(expression,"+-()*/ \t",true);

        // Process each token
        while(st.hasMoreTokens()) {
            String token = st.nextToken();
            System.out.println("Current Token: " + token);

            // Throw out blank spaces
            if (token.equals(" ") || token.equals("\t")){
                System.out.println("Space: " + token);
            }

            // Check if Operand.
            else if (!Operator.isOperator(token)){
                //If token is an operand push to operand stack
                System.out.println("...Pushing Operand: " + token);
                operands.push(Double.parseDouble(token));
            }

            // Just push start braces.
            else if(Operator.getOperator(token).isStartBrace()){
                System.out.println("...Pushing start brace: " + token);
                operators.push(Operator.getOperator(token));
            }
            // If an end brace, pop until a start brace is encountered.
            else if (Operator.getOperator(token).isEndBrace()){
                System.out.println("End brace encountered: " + token);
                System.out.println("...Currently on top of stack: " + operators.peek());

                while (!operators.peek().isStartBrace()) {


                    System.out.println("...While Currently on top of Operands Stack: " + operators.peek());
                    String a = operators.peek().toString();
                    Operator op = Operator.getOperator(a);
                    double pop1 = operands.pop();
                    double pop2 = operands.pop();
                    System.out.println("Calculating 2 ... : " + pop1 +  " " +  pop2);

                    operands.push(op.doCalc(pop2, pop1));
                    System.out.println("...... Calculated Number: " + operands.peek());
                    System.out.println("Popping off operator after calculation: " + operators.peek());
                    operators.pop();

                }

                operators.pop();

            }

            // Process the operators.  Pop until the precedence is lower, or end of stack found
            else { // Any other operator, check precedence
                while (!operators.isEmpty() && operators.peek().getPrecedenceLevel() >= Operator.getOperator(token).getPrecedenceLevel()){

                    String a = operators.peek().toString();
                    Operator op = Operator.getOperator(a);
                    double pop1 = operands.pop();
                    double pop2 = operands.pop();
                    System.out.println("Calculated Number 2: ... : " + pop1 +  " " +  pop2);

                    operands.push(op.doCalc(pop2, pop1));
                    operators.pop();


                }
                operators.push(Operator.getOperator(token)); //if precedence of Operators is greater than stack push to the stack

            }

            System.out.println("Current Token End: " + token);
            System.out.println("The Operators in stack: " + operators.stringify());
            System.out.println("The Operands in stack: " + operands.stringify());
            System.out.println("------------------- End Loop -------------------");

        } // End while

        // return the result, the only thing left on the stack
        System.out.println("The Operators in stack: " + operators.stringify());
        System.out.println("The Operands in stack: " + operands.stringify());

        return operands.pop();
    } // End evaluate expression
} // end class
