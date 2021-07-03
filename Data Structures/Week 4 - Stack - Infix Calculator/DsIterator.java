package com.infix;
import java.util.Iterator;
/**
 * The DsIterator allows a data structure to
 * be walked in both the forward and reverse
 * direction from any given element.
 * <p>
 * The Iterator and ListIterator classes in Java implemented
 * methods that were not wanted for this datastructures
 * package, specifically the ability to modify the
 * under laying data structure using methods like
 * add and remove on the iterator. The DsIterable and DsIterator
 * are created to work around them.
 * <p>
 * Note that DSIterator extends Iterator, so it can be used
 * in a foreach statement.
 *
 * @author Charles Kann
 *
 * @param <E> the type of elements to be iterated.
 */

/*
 * Modification history
 */

public interface DsIterator<E> extends Iterator<E> {
    /**
     *  Returns true if this list iterator has more elements
     *  when traversing the list in the forward direction.
     *  <p>
     *
     *  @return true if there are more elements.
     */
    public boolean hasNext();

    /**
     *  Returns true if this list iterator has more elements
     *  when traversing the list in the backward direction.
     *  <p>
     *
     *  @return true if there are more elements.
     */
    public boolean hasPrevious();

    /**
     *  Returns the next element in the list
     *  <p>
     *
     *  @return the next element in the list
     */
    public E next();

    /**
     *  Returns the previous element in the list
     *  <p>
     *
     *  @return the previous element in the list
     */
    public E previous();

    /**
     * sets the list to the first element so
     * hasNext and next can process
     * the list from beginning to end.  This
     * is the default for a list.
     */
    public void setIteratorToStart();

    /**
     * sets the list to the last element so
     * hasPrevious and previous can process
     * the list from end to beginning
     */
    public void setIteratorToEnd();

    /**
     *  removes the element from the collection.  It is allowable
     *  to not implement this operation and instead throw an
     *  UnsupportedOperationException.
     *
     *  For those collections that allow this, this remove must
     *  update the time stamp for the fail-fast behavior, and ensure
     *  that the hasNext and next behavior is correct.
     *
     *  For many data structures, the IndexedList in particular, the
     *  remove functionality are useful.  However to get this
     *  behavior correct is difficult, and the default behavior
     *  of throwing an UnsupportedOperationException, will be used.
     *
     */
    public void remove();
}
