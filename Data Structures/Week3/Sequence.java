/**
 * Description: consecutive 1 and 0's
 * Course  : JHU Data Structures Summer '21
 * Purpose : Homework 3 - Part 2.2
 * Date    : 6/23/21
 * @author Luis Peña
 */

import java.io.*;
import java.util.*;

public class Sequence {


public static void main(String[] args){


        System.out.println("Calculating Sequence");
        printSequence(3);

        System.out.println("Calculating Sequence");
        printSequence(5);

}


/**
 * @param int x : integer that is used to print the sequence.
 */

static void printSequence(int x) {

        // Base case
        if (x <= 0) {
                return;
        }

        char[] s = new char[x];

        // First character should always be 0
        s[0] = '0';

        // Generate string that start with 0
        calSequence(x, s, 1);

        // Start the first character to be 1
        s[0] = '1';
        calSequence(x, s, 1);

}

/**
 * @param int x : Last position of array value
 * @param char s : Array that stores the values of 1 and 0's
 * @param int n : Keeps tracks of index
 */
public static void calSequence (int x, char[] s, int n){

        // Exit if on last element
        if (n == x) {

                String finals = Arrays.toString(s);
                System.out.print(finals+" " + "\n");
                return;

        }

        // add 0 or 1 depending on current character
        if (s[n - 1] == '0') {

                s[n] = '0';
                calSequence(x, s, n + 1);

                s[n] = '1';
                calSequence(x, s, n + 1);
        }

        // add 0 if the current character is 1
        if (s[n - 1] == '1') {

                s[n] = '0';

                // Calculate next value in array
                calSequence(x, s, n + 1);

        }
}

}
