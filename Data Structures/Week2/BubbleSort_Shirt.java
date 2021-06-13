

import java.util.Comparator;

public class BubbleSort_Shirt {

	/**
	 * @param args args[0] the size of the array to sort
	 */
	public static void main(String[] args) {

        Shirt[] shirts = {new Shirt("xs",2),
        		          new Shirt("xl", 15),
        		          new Shirt("m", 25),
        		          new Shirt("l", 21),
        		          new Shirt("xxl", 5)};

        bubbleSort(shirts);               // Use Comparable (natural sort order)
        bubbleSort(shirts, new Shirt());  // Use Comparator



        // Check that it at least appears sorted

        for (Shirt s1 : shirts) {
        	System.out.println("for " + s1.size + " there are " + s1.inventory + " shirts");
        }
	}

	/* This is just to create the ability to call bubbleSort without any Comaparable */
	public static <T> void bubbleSort(Comparable<T>[] elements) {
		bubbleSort(elements, null);
	}

	public static <T> void bubbleSort(Comparable<T>[] elements, Comparator comp) {
	}

	public static void swap(final Shirt[] elements, int i, int j){
		Shirt temp = elements[i];
		elements[i] = elements[j];
		elements[j] = temp;
	}

	private static class Shirt implements Comparable<Shirt>, Comparator<Shirt>
	{
		String size;  //  size is xs, s, m, l, xl, xxl
		int inventory;

		public Shirt() {

		}

		public Shirt(String size, int inventory) {
			this.size = size;
			this.inventory = inventory;
		}

		public int compareTo(Shirt s) {
			return 0;
		}

		public int compare(Shirt s1, Shirt s2) {
			return 0;
		}
	}
}
