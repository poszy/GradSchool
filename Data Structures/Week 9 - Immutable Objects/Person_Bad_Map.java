import java.util.TreeMap;
public class Person_Bad_Map {

	public static void main(String[] args) {
		TreeMap<Person, Person> tm = new TreeMap<Person, Person>();
		Person p = new Person("Chuck", 30);
		tm.put(p, p);
		p = new Person("Cindy", 25);
		tm.put(p, p);
	    p = new Person("Kathy", 28 );
		tm.put(p, p);
		
		p = new Person("Ryan", 55);
		tm.put(p, p);

		p = tm.get(new Person("Chuck", 30));
		p.changeAge(18);

		// Chuck is in the keySet and the records
		for(Person pn: tm.keySet()) {
			System.out.println(pn);			
		}
		
		for(Person pn: tm.values()) {
			System.out.println(pn);			
		}
		
		// But Chuck is no where to be found.
		Person p1 = tm.get(new Person("Chuck", 18));
		System.out.println(p1);

	}

}
