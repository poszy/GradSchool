import java.io.*;
import java.util.*;

public class Sequence {


  public static void main(String[] args){


    System.out.println("Calculating Sequence");
    printSequence(3);

    System.out.println("Calculating Sequence");
    printSequence(5);

  }

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
