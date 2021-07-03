package com.infix;

public class QueueCircular<E extends Comparable<E>> implements Queue<E> {
  
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static int DEFAULT_SIZE = 10;
	
	private int numElements = 0;
	private int queueStart = 0;
	private int queueEnd = 0;
	private E[] elements;	
	
	@SuppressWarnings("unchecked")
	public QueueCircular(int size) {		
        numElements = 0;
        elements = (E[]) new Comparable[size];
	}	

	
	public QueueCircular() {
		this(DEFAULT_SIZE);
	}
	
	@Override
	public int size() {
        return numElements;
	}
	
	@Override
	public boolean isEmpty() {
		return (numElements == 0);
	}

	@Override
	public void clear() {
		numElements = 0;
	}

	@Override 
	public String debug() {
		String s = "\nDebug Circular Queue";
		s = s + "\nnumElements = " + numElements;
		s = s + "\n firstElement = " + queueStart;
		s = s + "\n lastElement = " + queueEnd;
		for (E e: elements) {
			s = s + "\n" + e;
		}
		
		return s;
	}

	@Override
	public void enqueue(E element) {
		if (numElements >= elements.length) {
			throw new ArrayIndexOutOfBoundsException("Max elements in Queue");
		}
		
		int nextElement = (queueStart + numElements) % elements.length;
		elements[nextElement] = element;
		queueEnd = nextElement;
		numElements++;	
	}

	@Override
	public E dequeue() {
		E retElement = elements[queueStart];
		queueStart = (queueStart + 1) % elements.length;
		numElements = numElements - 1;
		
		return retElement;
	}
	
	@Override
	public E peek() {
		E retElement = elements[queueStart];
		return retElement;
	}
	
	@Override
	public DsIterator<E> iterator() {
		// TODO Auto-generated method stub
		return null;
	}

}
