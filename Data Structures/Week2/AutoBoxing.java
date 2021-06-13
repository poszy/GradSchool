
public class AutoBoxing {

	public static void main(String[] args) {
    int length = 1000000000;
		int sum = 0;

		for (int i = 0; i < length; i++) {
			sum  = sum + 1;
		}

		System.out.println(sum);
		Integer sum1 = 0;

		for (Integer i = 0; i < length; i++) {
			sum1  = sum1 + 1;
		}

		System.out.println(sum1);

	}

}
