/**
 *  Name:       Listing4
 *  Author:     Charles Kann
 *  Date:       July 4, 2003
 *
 *  Purpose:    This program shows how if a mutable object
 *              is used as a key in a Hashtable, it is
 *              possible to completely lose the object.
 *
 */

import java.util.HashMap;
import java.util.Iterator;

/**
 *  The main program which shows how the key for "Kanga Roo" was lost.
 *  Note that it uses the "Name" class from Listing 3.
 */
public class Listing4 {
    public static void main(String args[]) {
        HashMap<Name, Object> table = new HashMap<Name, Object>();
        //
        Name name = new Name("Kanga", "Roo");
        table.put(name, new Object());

        // Now change the name object, losing the key.
        name.setName("Koola", "Bear");
        table.put(name, new Object());

        // Now try to find the object, which appears to be gone!

        if (table.get(new Name("Kanga", "Roo")) != null)
            System.out.println("Kanga Roo found");
        else
            System.out.println("Kanga Roo not found");

        // Kanga Roo is not a key, but Koola Bear is in twice!
        System.out.println("\nPrint the keys");
        Iterator i = (table.keySet()).iterator();
        while (i.hasNext()) {
            System.out.println(i.next());
        }
    }
}