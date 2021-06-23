public class Fibo {

  public static void main(String[] args){

    System.out.println("Calculating Fibonaci Sequence");

    for (int i = 0; i < 13; i++){
      System.out.println(calFibo(i));
    }

  }

  public static int calFibo (int x){
    if (x <= 1){
      return x;
    } else{

    return calFibo(x - 1) + calFibo(x -2);
    }

  }
}
