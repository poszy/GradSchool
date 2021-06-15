

import java.util.Comparator;
/**
 * Description: BubbleSort for Tshirts generics with Comparable and comparator
 * Course  : JHU Data Structures Summer '21
 * Purpose : Homework 2 - BubbleSort for Tshirts generics
 * Date    : 6/12/21
 * @author Luis Peña
 */
public class BubbleSort_Shirt {

	/**
	 * @param args args[0] the size of the array to sort
	 */
	public static void main(String[] args) {

        Shirt[] shirts = {new Shirt("xs ",2),
        		          new Shirt("xl ", 15),
        		          new Shirt("m  ", 25),
        		          new Shirt("l  ", 21),
        		          new Shirt("xxl", 5),
        		          new Shirt("s  ", 12)};

        bubbleSort(shirts);               // Use Comparable (natural sort order)
        // Check that it at least appears sorted
        for (Shirt s1 : shirts) {
        	System.out.println("for " + s1.size + " there are " + s1.inventory + " shirts");
        }

        bubbleSort(shirts, new Shirt());  // Use Comparator
        // Check that it at least appears sorted
        for (Shirt s1 : shirts) {
        	System.out.println("for " + s1.size + " there are " + s1.inventory + " shirts");
        }

	}

	/* This is just to create the ability to call bubbleSort without any Comaparable */
	public static <T extends Comparable<T>> void bubbleSort(T[] elements)
	{
		bubbleSort(elements, null);

	}

	public static <T extends Comparable<T>> void bubbleSort(T[] elements, Comparator<T> comp) {
		for (int i = 0; i < elements.length-1; i++) {
			for (int j = 0; j < elements.length-i-1; j++) {
				if (comp == null) {
					if (elements[j].compareTo(elements[j+1]) > 0)
						swap(elements, j, j+1);
				}
				else {
					if (comp.compare(elements[j], elements[j+1]) > 0)
					    swap(elements, j, j+1);;
				}
			}
		}

	}

	public static <E> void swap(final E[] elements, int i, int j){
		E temp = elements[i];
		elements[i] = elements[j];
		elements[j] = temp;
	}
}

class Shirt implements Comparable<Shirt>, Comparator<Shirt>
	{
		String size;  //  size is xs, s, m, l, xl, xxl
		int inventory;

		public Shirt() {

		}

		public Shirt(String size, int inventory) {
			this.size = size;
			this.inventory = inventory;
		}

		public int getSize() {
		    //  size is xs, s, m, l, xl, xxl
			if (this.size.equals("xs ")) return 0;
			if (this.size.equals("s  "))  return 1;
			if (this.size.equals("m  "))  return 2;
			if (this.size.equals("l  "))  return 3;
			if (this.size.equals("xl ")) return 4;
			return 5;
		}

		public int compareTo(Shirt shirt) {

				return this.inventory - shirt.inventory;

		}

		public int compare(Shirt s1, Shirt s2) {

			return (s1.inventory - s2.inventory);

		}
	}
