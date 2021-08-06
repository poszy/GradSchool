import java.util.TreeMap;
public class Person_Bad_Map {

	public static void main(String[] args) {

		TreeMap<Person, Person> tm = new TreeMap<Person, Person>();
		// key age gets hased into an array index

		Person p = new Person("Chuck", 30);

		// put that key in that location
		tm.put(p, p);


		p = new Person("Cindy", 25);
		tm.put(p, p);
	    p = new Person("Kathy", 28 );
		tm.put(p, p);
		
		p = new Person("Ryan", 55);
		tm.put(p, p);

		// Grab that chuck object
		p = tm.get(new Person("Chuck", 30));

		// And change the objects age . which is being used as the key
		p.changeAge(18);

		// Chuck is in the keySet and the records
		// so when we grab by key values, its there

		System.out.println(" Key Set************************************");
		for(Person pn: tm.keySet()) {
			System.out.println(pn);			
		}

		System.out.println("************************************");
		for(Person pn: tm.values()) {
			System.out.println(pn);			
		}

		System.out.println("Values Set ************************************");

		// But Chuck is no where to be found.

		// when we try and grab chuck by age. The object  of chuck : 30 was changed. overwritten with 18.
		// chuck already existed at chuck : 30 , after the object was changed
		// the index no longer has the key to the original chuck and since chuck:18 was inserted at the index of chuck:30
		Person p1 = tm.get(new Person("Chuck", 18));
		System.out.println(p1);
//		Person p1 = tm.get(new Person("Ryan", 55));
//		System.out.println(p1);
//
//		// Since Chuck is still stored in the index where ryan is at.
//		// We can simply override it back to its origional value and pull the record back
//		p = new Person("Ryan", 55);
//		tm.put(p, p);
//		Person p2 = tm.get(new Person("Ryan", 55));
//		System.out.println(p2);
	}

}
