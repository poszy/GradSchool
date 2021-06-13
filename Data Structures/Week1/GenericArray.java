import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

class GenericArray<E> implements Iterable<E>, Serializable {

private static final long serialVersionUID = 1L;
private E[] elements;

@SuppressWarnings("unchecked")
public GenericArray(int size) {
        this.elements = (E[]) new Object[size];
}
public void set(int pos, E p){
        // Why is the check not needed?
        if ((pos >= 0) && (pos < elements.length)) {
                elements[pos]=p;
        }
        else{
                throw new ArrayIndexOutOfBoundsException();
        }
}

public E get(int pos){
        // Why is the check not needed?
        if ((pos >= 0) && (pos < elements.length)) {
                return elements[pos];
        }
        else {
                throw new ArrayIndexOutOfBoundsException();
        }
}

public int length() {
        return elements.length;
}
public Iterator<E> iterator() {
        return (Iterator<E>) new GenericIterator();
}
private class GenericIterator implements Iterator {

int item;
public GenericIterator() {
        item = 0;
}
@Override
public boolean hasNext() {
  int item = 0;

// If there is a next item, return true, else return false.
if (item < elements.length) {
  return true;
}
else {
  return false;
}

}
@SuppressWarnings("unchecked")
@Override
public E next() {
        // save the element at position item
        // increment item
        // return the saved element
        E retS = elements[item];
        item++;
        return retS;
}
}


public static void main(String args[]) {
        // To see why I hate Java Lambda functions, uncomment the next line.
        //String x = "2";
        String sx = null;
        GenericArray<String> ga= new GenericArray<String>(5);
        ga.set(0,  "Abc");
        ga.set(2,  "Def");
        for (Iterator<String> s = ga.iterator(); s.hasNext(); ) {
                sx = s.next();
                System.out.println(sx);
        }
        for(String s : ga) {
                System.out.println(s);
        }
        ga.forEach(x->System.out.println(x));
}


}
