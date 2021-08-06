/**
 *  Name:       MutableKey
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
 *  A mutable Name class, used as a key to a HashMap
 */
class Name {
    String firstName;
    String lastName;

    public Name(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public void setName(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public boolean equals(Object name) {
        Name toCheck = (Name) name;
        return (firstName.equals(toCheck.firstName) &&
                lastName.equals(toCheck.lastName));
    }

    public int hashCode() {
        String s = firstName + lastName;
        return s.hashCode();
    }

    public String toString() {
        return (firstName + " " + lastName);
    }
}


/**
 *  A Main program that shows the insertion of the key "Kanga Roo"
 *  into a HashMap.
 */
public class Listing3 {
    public static void main(String args[]) {
        HashMap<Name, Object> table = new HashMap<Name, Object>();

        // Add and retrieve "Kanga" "Ro"
        Name name = new Name("Kanga", "Roo");
        table.put(name, new Object());
        if (table.get(new Name("Kanga", "Roo")) != null)
            System.out.println("Kanga Roo found");
        else
            System.out.println("Kanga Roo not found");
    }
}