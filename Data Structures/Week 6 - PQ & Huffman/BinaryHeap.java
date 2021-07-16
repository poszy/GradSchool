/**
 * 
 */
package huffman;



/**
 * @author chuck
 *
 */
public class BinaryHeap<E extends Comparable<E>> implements PriorityQueue<E> {

	private static final long serialVersionUID = 1L;
	private E[] elements;
    private int size;
    private static int DEFAULT_SIZE = 127; // 7 levels;
    
    public BinaryHeap() {
    	this(DEFAULT_SIZE);  	
    }

	@SuppressWarnings("unchecked")
	public BinaryHeap(int size) {
		// Some math to get the correct size of the tree.
		int power = (int) (Math.log(size) / Math.log(2));
		int newSize = (int) Math.pow(2, power+1);
		elements = (E[]) new Comparable[newSize];
	}

	@Override 	
	@SuppressWarnings("unchecked")
	public void buildPriQueue(E[] newElements) throws DataStructureException {
		if (size > 0)
			throw new DataStructureException("A build heap must be empty");
		if (newElements.length >= (elements.length+1))
		{
		    // Some math to get the correct size of the tree.
			int power = (int) (Math.log(newElements.length) / Math.log(2));
			int newSize = (int) Math.pow(2, power);
			elements = (E[]) new Comparable[newSize];
			size = 0;
		}
		
		// Change to heapify operation
		for (E element: newElements) {
			insert(element);
		}
	}

	@Override
	public void insert(E element)
	{
		int nextSpace;
		if (size > elements.length-1)
			reallocate();
		
		size = size + 1;
		
		// bubble down
		for (nextSpace = size; 
				nextSpace > 1 && (element.compareTo(elements[nextSpace/2]) < 0);
				nextSpace = nextSpace /2 )
		{
			elements[nextSpace] = elements[nextSpace / 2];
		}
		elements[nextSpace] = element;
	}

	@Override
	public E findFirst()
	{
		return elements[1];
	}

	@Override
	public E deleteFirst() {

		if (size == 0) {
			throw new ArrayIndexOutOfBoundsException("No elements in heap");
		}
		E retElement = elements[1];
		
		// Move last element to first element
		elements[1] = elements[size];
		size = size - 1;	
		
		// bubble down
		int childIdx = 1;
        while((childIdx*2) <= size)
        {
        	// Choose the smallest child
        	int selectedChild = childIdx * 2;
        	if ((selectedChild + 1) <= size) {
	        	if (elements[selectedChild].compareTo(
	        		elements[selectedChild + 1]) > 0) {
	        		selectedChild = selectedChild + 1;
	        	}
        	}
        	
        	// swap with smallest child if needed.
        	if (elements[selectedChild].compareTo(
        		elements[childIdx]) < 0) {
        		E temp = elements[childIdx];
        		elements[childIdx] = elements[selectedChild];
        		elements[selectedChild] = temp;

        	}
        	else // item in correct place
        		break;     
        	
    		childIdx = selectedChild;
        }
			
		return retElement;
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public DsIterator<E> iterator() {
		return new BinaryHeapIterator();
	}

	@Override
	public boolean isEmpty() {
		return (size == 0);
	}

	@Override
	public void clear() {
		size = 0;		
	}
	
	@SuppressWarnings("unchecked")
	private void reallocate() {
		int allocationSize = (size() * 2) + 1;
		E[] realloc = (E[]) new Comparable[allocationSize];  
		System.arraycopy(elements, 0, realloc, 0, size);
		elements = realloc;
	}
	private class BinaryHeapIterator 
	    implements DsIterator<E> {
		
		private int current = 1;

		@Override
		public boolean hasNext() {
			return current <= size;
		}

		@Override
		public boolean hasPrevious() {
			throw new UnsupportedOperationException();
		}

		@Override
		public E next() {
			return elements[current++];
		}

		@Override
		public E previous() {
			throw new UnsupportedOperationException();
		}

		@Override
		public void setIteratorToStart() {
			throw new UnsupportedOperationException();	
		}

		@Override
		public void setIteratorToEnd() {
			throw new UnsupportedOperationException();
		}

		@Override
		public void remove() {
			throw new UnsupportedOperationException();
		}
	}
}
