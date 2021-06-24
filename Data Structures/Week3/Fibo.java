/**
 * Description: Fibbonaci Sequence Recursively
 * Course  : JHU Data Structures Summer '21
 * Purpose : Homework 3 - Part 2.1
 * Date    : 6/23/21
 * @author Luis Peña
 */

public class Fibo {

  public static void main(String[] args){

    System.out.println("Calculating Fibonaci Sequence");

    for (int i = 0; i < 13; i++) {

            System.out.println(calFibo(i));

    }

  }

  /**
   * @param int x : integer that is used to calculate the fibo sequence.
   */
  public static int calFibo (int x){

    // Base Case
    if (x <= 1) {

            return x;

    } else{

            // Call Recursively
            return calFibo(x - 1) + calFibo(x -2);
    }

  }

}
