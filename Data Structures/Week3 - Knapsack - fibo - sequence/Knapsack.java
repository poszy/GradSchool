/**
 * Description: calSack Party
 * Course  : JHU Data Structures Summer '21
 * Purpose : Homework 3 - Part 3 Extra Credit
 * Date    : 6/23/21
 * @author Luis Peña
 */

public class Knapsack {

  public static void main(String[] args){
        int totalValue[] = new int[] {4, 5, 2, 7, 2, 2, 3};
        int weight[] = new int[] {4, 5, 2, 7, 2, 2, 3};
        int maxWeight = 10;
        int totalLength = totalValue.length;

        System.out.println(calSack(maxWeight, weight, totalValue, totalLength));
    }

    /**
     * @param int x , y : find the max of two numbers
     */
  public static int getMax(int x, int y) { return (x > y) ? x : y; }


  /**
   * @param int maxWeight : Max wieght
   * @param int weight : weight of each item in array
   * @param int totalValue : Value of each item in array
   * @param int totalLength : length of array
   */

  public static int calSack(int maxWeight, int weight[], int totalValue[], int totalLength){
          // Base Case
          if (totalLength == 0 || maxWeight == 0){
              return 0;
          }

          if (weight[totalLength - 1] > maxWeight){
              return calSack(maxWeight, weight, totalValue, totalLength - 1);
          }


          else{

              return getMax(totalValue[totalLength - 1] + calSack(maxWeight - weight[totalLength - 1], weight, totalValue, totalLength - 1),
                         calSack(maxWeight, weight, totalValue, totalLength - 1));
          }


      }

}
