import java.util.Random;
import java.util.*;
/**
 * Description: Find min, max and total of an array.
 * Course  : JHU Data Structures Summer '21
 * Purpose : Homework 2 - FirstArrayAssignment
 * Date    : 6/12/21
 * @author Luis Peña
 */
public class MinMaxTotal {

	/**
	 * @param args args[0] the size of the array to sort
	 */
	public static void main(String[] args) {
		if (args.length != 1){
			System.out.println("Run this program as " +
		        "'java MinMaxTotal [array size]'" );
			System.exit(1);
		}

        int arraySize = Integer.parseInt(args[0]);
        int[] elements = new int[arraySize];

        Random randomNumber = new Random();

        for (int i = 0; i < elements.length; i++){
            elements[i] = randomNumber.nextInt();
        }

        long startTime = System.currentTimeMillis();

        int max = findMax(elements);
        int min = findMin(elements);
        int total = calcTotal(elements);


        long endTime = System.currentTimeMillis();
        // Check that it at least appears sorted
        System.out.println("Max = " + max + "; Min = " + min + "; Total = " + total );
        System.out.println();

        System.out.println("For " +
            arraySize + " elements, the program ran " +
           (endTime - startTime) + " milliseconds ");
	}

	public static int findMax(int[] elements) {
    int max = elements[0];

    System.out.println("Array Elements: " + Arrays.toString(elements));
    for (int i = 0; i < elements.length; i++){
      if (elements[i] > max){
        max = elements[i];
      }
    }

    return max;
	}

	public static int findMin(int[] elements) {
    int min = elements[0];

    for (int i = 0; i < elements.length; i++){
      if (elements[i] < min){
        min = elements[i];
      }
    }

    return min;

  }

	public static int calcTotal(int[] elements) {
		int total = 0;

    for (int i = 0; i < elements.length; i++){
      total = total + elements[i];
    }
    return total;
	}
}
