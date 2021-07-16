package huffman;

public interface PriorityQueue<E extends Comparable<E>>
		extends Collection<E> {
	public void buildPriQueue(E[] elements) throws DataStructureException;
	public void insert(E element);
	public E findFirst();
	public E deleteFirst();

}
