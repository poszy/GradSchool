package com.infix;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;



public class RPN_Calc {

    public static void main(String args[]) {
        try {

            // Set up the Buffered reader to read a line.
            System.out.println("Enter RPN equation");
            BufferedReader br =
                    new BufferedReader(new InputStreamReader(System.in));

            // Return postfix notation
            System.out.println("Postfix notation is "+ calculate(br.readLine()));
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public static double calculate(String infix)
            throws InvalidOperatorException{

        String postfix = "";
        Stack<Double> operands = new ArrayListStack<Double>();
        StringTokenizer st = new StringTokenizer(infix,"+-*/ \t",true);

        while(st.hasMoreTokens()) { //if tokenizer has token continue through loop until none when while becomes false and exits loop
            String token = st.nextToken();

            if (token.equals(" ") || token.equals("\t")); //remove blank space and tabs from string

            else if (!Operator.isOperator(token)){ //If token is an operand put it on string
                operands.push(Double.parseDouble(token));
            }

            else {
                Operator op = Operator.getOperator(token);
                double pop1 = operands.pop();
                double pop2 = operands.pop();
                operands.push(op.doCalc(pop2, pop1));
            }
        }
        return operands.pop();
    }
}
