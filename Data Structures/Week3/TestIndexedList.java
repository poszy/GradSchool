/**
 *
 */
package testdatastructures;

import com.chuckkann.datastructures.DataStructureException;
import com.chuckkann.datastructures.DsIterator;
import com.chuckkann.datastructures.IndexedList;
import com.chuckkann.datastructures.indexedlist.ArrayList;
import com.chuckkann.datastructures.indexedlist.DoubleLinkedList;
import com.chuckkann.datastructures.indexedlist.InArrayLinkedList;
import com.chuckkann.datastructures.indexedlist.LinkedList;


/**
 * @author Charl
 *
 */
public class TestIndexedList {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		IndexedList<String> list = new LinkedList<String>();;

		System.out.println("IndexedList cases 1 - adding individual items");
		list.add("input 1");
		list.add("input 2");
		list.add("input 3");
		list.add("input 4");

		System.out.println("Printing reversd list");
		list.printReverse();

	}
}
