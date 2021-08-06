/**
 *  A immutable Name class
 */
final class Name {
    private String firstName;
    private String lastName;

    public Name(Stringbuffer firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }
    
    public StringBuffer getFirstName() {
        return firstName;
    }

    public String toString() {
        return (firstName + " " + lastName);
    }
    
    public static void main(String... args) {
        StringBuffer s = new StringBuffer(100);
        s.append("Chuck");
        String s1 = "kann";
        Name n = new Name(s, s1);
        s1.append("and me");
        StringBuffer s2 = n.getFirstName();
        s2.append(" Kann");
    }
}

