package bet;


import java.util.Comparator;



/**
 * An array based implementation of a IndexedList.
 *
 * @author Charles Kann
 *
 * @param <E> The elements to be stored in the list.  The
 * elements must be Comparable because the contains and
 * remove operations will use the Comparable interface
 * compare method to check for equality
 */

/*
 * Modification History
 */

public class ArrayList<E extends Comparable<E>> implements IndexedList<E> {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private static int DEFAULT_SIZE = 10;

    private int numElements;
    private E[] elements;

    /**
     * Constructor to create an instance of the class.
     * <p>
     * Runtime Growth - Note that the new operator on the
     *     array puts a null in each value, and this operation
     *     surprisingly grows in O(n) time.
     * </p>
     * @param size The initial size of the array to create.
     */
    @SuppressWarnings("unchecked")
    public ArrayList(int size) {
        numElements = 0;
        elements = (E[]) new Comparable[size];
    }

    /**
     * Default constructor, create an instance of the
     * class with the default size.
     */
    public ArrayList(){
        this(DEFAULT_SIZE);
    }

    /* (non-Javadoc)
     * @see datastructures.Collection#size()
     */

    @Override
    public int size() {
        return numElements;
    }

    /* (non-Javadoc)
     * @see datastructures.Collection#iterator()
     */

    @Override
    public DsIterator<E> iterator() {
        return (DsIterator<E>) new ListArraytDsIterator();
    }

    /* (non-Javadoc)
     * @see datastructures.Collection#isEmpty()
     */

    @Override
    public boolean isEmpty() {
        if (numElements == 0)
            return true;
        else
            return false;
    }

    /* (non-Javadoc)
     * @see datastructures.Collection#clear()
     */

    @Override
    public void clear() {
        numElements = 0;
    }

    /* (non-Javadoc)
     * @see datastructures.IndexedList#add(java.lang.Comparable)
     */

    @Override
    public void printReverse() {

    }

    /**
     *  Run time Growth
     *  Best Case - O(1)
     *  Average - O(1)
     *  Worst Case - O(1)
     */
    @Override
    public void add(E element) {
        add(numElements, element);
    }

    /* (non-Javadoc)
     * @see datastructures.IndexedList#add(int, java.lang.Comparable)
     */
    /**
     *  Run time Growth
     *  Best Case - O(1)  // adding to the end of the list
     *  Average - O(n)
     *  Worst Case - O(n)
     */
    @Override
    public void add(int index, E element) {

        // index must be in List (not just elements)
        // if not, throw ArrayIndexOutOfBoundsException
        if (index < 0 || index > numElements) {
            throw new ArrayIndexOutOfBoundsException();
        }

        // if the new element makes the size too large,
        // reallocate the underlaying elements array.
        if (numElements == elements.length)
            elements = reallocateArray();

        // move all elements to prepare space for new
        // element.
        for (int i = numElements -1 ; i >= index; i--) {
            elements[i+1] = elements[i];
        }

        // add new element
        elements[index] = element;
        numElements = numElements + 1;
    }

    /* (non-Javadoc)
     * @see datastructures.IndexedList#add(java.lang.Collection)
     */

    @Override
    public void add(Collection<E> elementsCollection) throws DataStructureException {
        if (this == elementsCollection)
            throw new DataStructureException("You cannot add a Collection to itself");

        add(numElements, elementsCollection);
    }


    /* (non-Javadoc)
     * @see datastructures.IndexedList#add(int, java.lang.Collection)
     */

    @Override
    public void add(int index, Collection<E> elementsCollection)
            throws DataStructureException {

        // index must be in List (not just the elements array)
        // if index is not in the list, throw
        // ArrayIndexOutOfBoundsException
        if (index < 0 || index > numElements)
            throw new IndexOutOfBoundsException();

        // Check to make sure collections are not the same.
        // This is necessary because this really messes
        // up the list.
        if (this == elementsCollection)
            throw new DataStructureException("You cannot add a Collection to itself");

        // Add items to the list.
        //
        // Note that this is
        // simple to code, but very inefficient.  See
        // the problems at the end of the chapter on
        // why this is inefficient, and how to greatly
        // improved it.
        int ip = index;  // ip is insertion point
        for (E element: elementsCollection)
        {
            this.add(ip, element);
            ip = ip + 1;
        }
    }

    /* (non-Javadoc)
     * @see datastructures.IndexedList#add(java.lang.Comparable[])
     */

    @Override
    public void add(E[] elementsArray) {
        add(numElements, elementsArray);
    }

    /* (non-Javadoc)
     * @see datastructures.IndexedList#add(int, java.lang.Comparable[])
     */

    @Override
    public void add(int index, E[] elementsArray) {

        // index must be in List (not just elements)
        // if not, throw ArrayIndexOutOfBoundsException
        if (index < 0 || index > numElements)
            throw new IndexOutOfBoundsException();

        // Add items to the list.
        //
        // Note that this is
        // simple to code, but very inefficient.  See
        // the problems at the end of the chapter on
        // why this is inefficient, and how to greatly
        // improved it.
        int ip = numElements;  // ip is insertion point
        for (E element: elementsArray)
        {
            this.add(ip, element);
            ip = ip + 1;
        }
    }


    /* (non-Javadoc)
     * @see datastructures.IndexedList#remove(int)
     */
    @Override
    public E remove(int index) {
        if (index < 0 || index >= numElements)
            throw new IndexOutOfBoundsException();

        E retElement = elements[index];
        numElements = numElements - 1;
        for (int i = index; i < numElements; i++) {
            elements[i] = elements[i+1];
        }
        return retElement;
    }

    /* (non-Javadoc)
     * @see datastructures.IndexedList#get(int)
     */
    @Override
    public E retrieve(int index) {
        if (index < 0 || index >= numElements)
            throw new IndexOutOfBoundsException();

        return elements[index];
    }

    /* (non-Javadoc)
     * @see datastructures.IndexedList#set(int, java.lang.Comparable)
     */
    @Override
    public E set(int index, E element) {
        if (index < 0 || index >= numElements)
            throw new IndexOutOfBoundsException();

        E retElement = elements[index];
        elements[index] = element;
        return retElement;
    }

    /* (non-Javadoc)
     * @see datastructures.IndexedList#indexOf(java.lang.Comparable)
     */
    @Override
    public int indexOf(E element) {
        return indexOf(0, element, null);
    }

    /* (non-Javadoc)
     * @see datastructures.IndexedList#indexOf(int index, java.lang.Comparable)
     */
    @Override
    public int indexOf(int index, E element) {
        return indexOf(index, element, null);
    }

    /* (non-Javadoc)
     * @see datastructures.IndexedList#indexOf(java.lang.Comparable, java.util.Comparator)
     */
    @Override
    public int indexOf(E element, Comparator<E> comparator) {
        return indexOf(0, element, comparator);
    }

    /* (non-Javadoc)
     * @see datastructures.IndexedList#indexOf(int java.lang.Comparable, java.util.Comparator)
     */

    @Override
    public int indexOf(int index, E element, Comparator<E> comparator) {
        if (index < 0 || index >= numElements)
            throw new IndexOutOfBoundsException();

        for (int i = index; i < numElements; i++) {
            if (comparator == null) {
                if (element.compareTo(elements[i]) == 0)
                    return i;
            }
            else {
                if (comparator.compare(element, elements[i]) == 0)
                    return i;
            }
        }
        return -1;
    }
    /* (non-Javadoc)
     * @see datastructures.IndexedList.lastIndexOf(int java.lang.Comparable)
     */
    @Override
    public int lastIndexOf(E element) {
        return lastIndexOf(element, null);

    }

    /* (non-Javadoc)
     * @see datastructures.IndexedList#lastIndexOf(int java.lang.Comparable, java.util.Comparator)
     */
    @Override
    public int lastIndexOf(E element, Comparator<E> comparator) {

        if (comparator == null)
            return lastIndexOf(element);

        for (int i = numElements-1; i > 0; i--) {
            if (comparator == null) {
                if (element.compareTo(elements[i]) == 0)
                    return i;
            }
            else {
                if (comparator.compare(element, elements[i]) == 0) {
                    return i;
                }
            }
        }
        return -1;
    }

    /* (non-Javadoc)
     * When the limit of the array has been reached, this method
     * grow the size of the array used to store the data.
     * The array is always made twice as big.
     */
    private E[] reallocateArray() {
        return reallocate(elements.length * 2);
    }

    @SuppressWarnings("unchecked")
    private E[] reallocate(int allocationSize) {
        if (allocationSize < elements.length * 2)
            allocationSize = elements.length * 2;
        E[] realloc = (E[]) new Comparable[allocationSize];
        System.arraycopy(elements, 0, realloc, 0, numElements);
        return realloc;
    }

    /* (non-Javadoc)
     * ArrayList Dsterator, used to iterator over the list.
     */

    private class ListArraytDsIterator implements DsIterator<E> {
        int currentElement;

        public ListArraytDsIterator(){
            currentElement = - 1;
        }

        public boolean hasNext() {
            if ((currentElement+1) >= numElements)
                return false;
            return true;
        }

        public E next() {
            currentElement = currentElement + 1;
            return (E)elements[currentElement];
        }

        @Override
        public boolean hasPrevious() {
            if ((currentElement - 1) >= 0)
                return true;
            return false;
        }

        @Override
        public E previous() {
            currentElement = currentElement - 1;
            return (E)elements[currentElement];
        }

        @Override
        public void setIteratorToStart() {
            currentElement = -1;
        }

        @Override
        public void setIteratorToEnd() {
            currentElement = numElements;
        }

        @Override
        public void remove() {
            for (int i = currentElement; i < numElements; i++) {
                elements[i] = elements[i+1];
            }
            numElements = numElements - 1;
            currentElement = currentElement - 1;
        }
    }
}
