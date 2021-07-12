package bet;


/*
 * @(#)ArrayList.java 2002/07/21
 * @author Charles W. Kann III
 *
 * Permission to use, copy, modify, and distribute this
 * software and its documentation for NON-COMMERCIAL purposes
 * and without fee is hereby granted provided that this
 * copyright notice appears in all copies.
 *
 * THE AUTHOR MAKES NO REPRESENTATIONS OR WARRANTIES ABOUT THE
 * SUITABILITY OF THE SOFTWARE, EITHER EXPRESS OR IMPLIED,
 * INCLUDING BUT NOT LIMITED TO THE IMPLIED WARRANTIES OF
 * MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE, OR
 * NON-INFRINGEMENT. THE AUTHOR SHALL NOT BE LIABLE FOR ANY DAMAGES
 * SUFFERED BY LICENSEE AS A RESULT OF USING, MODIFYING OR
 * DISTRIBUTING THIS SOFTWARE OR ITS DERIVATIVES.
 */




/**
 *  Purpose: This class defines a simplified ArrayList
 */

public class ArrayListStack<E extends Comparable<E>> implements Stack<E> {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private IndexedList<E> elements;


    /*
        No parameter constructor
    */
    public ArrayListStack() {
        elements = new ArrayList<E>();
    }

    /*
        constructor that allows the initial capacity to be set by the user
    */

    public ArrayListStack(int size) {
        elements = new ArrayList<E>(size);
    }

    /*
        Retrieves the element from the end of the stack

        @return the element at the end of the stack.
        @throws IndexOutOfBoundsException

        side effects: the last element in the stack is removed.
    */
    public E pop() {
        return elements.remove(elements.size() - 1);
    }

    /*
       retrieves the item at the end of the stack.

       @return the element at the end of the stack.

       side effects: None.  The stack is unchanged.

    */
    public E peek() {
        return elements.retrieve(elements.size() - 1);
    }

    /*
       sets the element at position index to element,
       and returns the previous value

       @param element element to add at the end of the stack.

       side effects: The element is added at the end of the stack.

    */
    public void push(E element) {
        elements.add(element);
    }

    /*
        returns the number of elements in the list

        @return number of elements in the list

        side effects: none
    */
    public int size() {
        return elements.size();
    }

    /*
        returns true if the stack is empty, otherwise false.

        side effect: none
    */
    public boolean isEmpty() {
        return elements.isEmpty();
    }

    /*
        returns an iterator for this ArrayList

        @returns list iterator

        side effect: none
    */

    public void clear() {
        elements.clear();
    }

    public DsIterator<E> iterator() {
        return elements.iterator();
    }
}