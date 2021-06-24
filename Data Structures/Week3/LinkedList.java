package com.chuckkann.datastructures.indexedlist;

import java.util.Comparator;

import com.chuckkann.datastructures.Collection;
import com.chuckkann.datastructures.DataStructureException;
import com.chuckkann.datastructures.DsIterator;
import com.chuckkann.datastructures.IndexedList;

/**
 * Doubly Linked List
 * @author chuck
 * @version 0.2.0 by Jihyeon Ryu
 * @param <E>
 *
 * Added previous pointer to in the node.
 * The program can iterate forward and backward.
 * The remove method can reconnect the previous node and the next node of the removed node to each other.
 * Fixed lastIndexOf().
 */
public class LinkedList<E extends Comparable<E>> implements IndexedList<E> {
	private static final long serialVersionUID = 1L;
	private int numElements;
	private Node<E> head;
	private Node<E> tail;

	public void printReverse() { reverse1(head);}

	private void reverse1(Node next) {


		if(next == null) { return; }

		reverse1(next.nextPtr);

		System.out.println(next);



	}

	/**
	 * Constructor to create an instance of the class.
	 *
	 * @param size The initial size of the array to create.
	 */
	public LinkedList() {
		numElements = 0;
		head = null;
		tail = null;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.chuckkann.datastructures.Collection#size()
	 */
	@Override
	public int size() {
		return numElements;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.chuckkann.datastructures.Collection#iterator()
	 */
	@Override
	public DsIterator<E> iterator() {
		return (DsIterator<E>) new ListLinkedInArrayIterator();
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.chuckkann.datastructures.Collection#isEmpty()
	 */
	@Override
	public boolean isEmpty() {
		return (numElements == 0);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.chuckkann.datastructures.Collection#clear()
	 */
	@Override
	public void clear() {
		numElements = 0;
		head = null;
		tail = null;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.chuckkann.datastructures.IndexedList#add(java.lang.Comparable)
	 */
	@Override
	public void add(E element) {
		add(numElements, element);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.chuckkann.datastructures.IndexedList#add(int, java.lang.Comparable)
	 */
	@Override
	public void add(int index, E element) {
		// check if index is valid
		if (index < 0 || index > numElements)
			throw new ArrayIndexOutOfBoundsException();

		//
		// Add element to the IndexedList
		//

		// Add item to list
		// case 1 : add at start of list
		if (index == 0) {
			if (head == null) { // IndexedList is empty
				tail = head = new Node<E>(element, null, null);
			} else { // IndexedList has elements
				Node<E> newHead = new Node<E>(element, head, null);
				head.setPrevPtr(newHead);
				head = newHead;
			}
			numElements = numElements + 1;
		}

		// case 2 : add at end of list
		else if (index == numElements) {
			Node<E> next = new Node<E>(element, null, tail);
			tail.setNextPtr(next);
			tail = next;
			numElements = numElements + 1;
		}

		// case 3 : add is in the middle of the list
		else {
			// Find the insertion point
			Node<E> foundPtr = findPrevPtr(index);

			// Insert new Node with element
			Node<E> newPtr = new Node<E>(element, foundPtr.getNextPtr(), foundPtr);
			foundPtr.nextPtr.setPrevPtr(newPtr);
			foundPtr.setNextPtr(newPtr);
			numElements = numElements + 1;
		}
	}

	@Override
	public void add(Collection<E> elementsCollection) throws DataStructureException {
		if (this == elementsCollection)
			throw new DataStructureException("You cannot add a Collection to itself");

		add(numElements, elementsCollection);

	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.chuckkann.datastructures.IndexedList#add(int,
	 * com.chuckkann.datastructures.Collection)
	 */
	@Override
	public void add(int index, Collection<E> elementsCollection) throws DataStructureException {
		// If the add(index, element) method is called, it requires that the chain is
		// walked
		// each time to find the insertion point. This is O(m*n), where n is the size
		// of the original collection, and m is size of the added collection. This
		// could be O(n^2) if n and m are approximately the same size. Make a better
		// implementation.
		if (this == elementsCollection)
			throw new DataStructureException("You cannot add a Collection to itself");

		int ip = index; // ip is insertion point
		for (E element : elementsCollection) {
			this.add(ip, element);
			ip = ip + 1;
		}

	}

	@Override
	public void add(E[] elementsArray) {
		add(numElements, elementsArray);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.chuckkann.datastructures.IndexedList#add(int,
	 * java.lang.Comparable[])
	 */
	@Override
	public void add(int index, E[] elementsArray) {
		if (index < 0 || index > numElements)
			throw new IndexOutOfBoundsException();

		// The add(index, element) method is called, it requires that the chain is
		// walked
		// each time to find the insertion point. This is O(m*n), where n is the size
		// of the original collection, and m is size of the added collection. This
		// could be O(n^2) if n and m are approximately the same size.
		// Make a better implementation.
		int ip = index; // ip is insertion point
		for (E element : elementsArray) {
			this.add(ip, element);
			ip = ip + 1;
		}
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.chuckkann.datastructures.IndexedList#remove(int)
	 */
	@Override
	public E remove(int index) {
		// check if index is valid
		if (index < 0 || index >= numElements)
			throw new ArrayIndexOutOfBoundsException();

		E retVal = null;
		// First item on the list
		if (index == 0) {
			retVal = head.getElement();
			head = head.getNextPtr();
			head.setPrevPtr(null);
			numElements = numElements - 1;

			if (head == null) {
				head = tail;
			}
		}

		// Last item on the list
		else if (index == (numElements - 1)) {
			retVal = tail.getElement();
			tail = tail.getPrevPtr();
			tail.setNextPtr(null);
			numElements = numElements - 1;
		}

		// Middle item on the list
		else {
			// Stop one before the current item to get a pointer to the item to remove
			// Get the node one after the index point
			// Get the value of the item to return it.
			Node<E> ptr = findPrevPtr(index);
			Node<E> next = findNextPtr(index);
			retVal = ptr.getNextPtr().getElement();

			// Take element out of list
			// Put free item on free space
			ptr.setNextPtr(next);
			next.setPrevPtr(ptr);
			numElements = numElements - 1;
		}

		return retVal;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.chuckkann.datastructures.IndexedList#retrieve(int)
	 */
	@Override
	public E retrieve(int index) {
		// check if index is valid
		if (index < 0 || index >= numElements)
			throw new ArrayIndexOutOfBoundsException();

		Node<E> nextPtr = head;
		for (int i = 0; i < index; i++) {
			nextPtr = nextPtr.getNextPtr();
		}

		return nextPtr.getElement();
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.chuckkann.datastructures.IndexedList#set(int, java.lang.Comparable)
	 */
	@Override
	public E set(int index, E element) {
		// check if index is valid
		if (index < 0 || index >= numElements)
			throw new ArrayIndexOutOfBoundsException();

		Node<E> ptr = findPrevPtr(index);
		E retVal = ptr.getElement();
		ptr.setValue(element);

		return retVal;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.chuckkann.datastructures.IndexedList#indexOf(java.lang.Comparable)
	 */
	@Override
	public int indexOf(E element) {
		return indexOf(0, element, null);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.chuckkann.datastructures.IndexedList#indexOf(int,
	 * java.lang.Comparable)
	 */
	@Override
	public int indexOf(int index, E element) {
		return indexOf(index, element, null);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.chuckkann.datastructures.IndexedList#indexOf(java.lang.Comparable,
	 * java.util.Comparator)
	 */
	@Override
	public int indexOf(E element, Comparator<E> comparator) {
		return indexOf(0, element, comparator);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.chuckkann.datastructures.IndexedList#indexOf(int,
	 * java.lang.Comparable, java.util.Comparator)
	 */
	@Override
	public int indexOf(int index, E element, Comparator<E> comparator) {
		// check if index is valid
		if (index < 0 || index > numElements)
			throw new ArrayIndexOutOfBoundsException();

		// Ignore all nodes up to the index node
		Node<E> ptr = head;
		for (int i = 0; i < index; i++) {
			ptr = ptr.getNextPtr();
		}

		// Loop to find the index of the next element matching
		// by comparator or comparable. If not found, return -1.

		for (int i = index; i < numElements; i++) {
			if (comparator != null) {
				if (comparator.compare(ptr.getElement(), element) == 0)
					return i;
			} else {
				if ((ptr.getElement()).compareTo(element) == 0)
					return i;
			}
			ptr = ptr.getNextPtr();
		}

		return -1;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.chuckkann.datastructures.IndexedList#lastIndexOf(java.lang.Comparable)
	 */
	@Override
	public int lastIndexOf(E element) {
		return lastIndexOf(element, null);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.chuckkann.datastructures.IndexedList#lastIndexOf(java.lang.Comparable,
	 * java.util.Comparator)
	 */
	@Override
	public int lastIndexOf(E element, Comparator<E> comparator) {
		for (int i = numElements - 1; i >= 0; i--) {
			Node<E> ptr = findPrevPtr(i + 1);
			if (comparator != null) {
				if (comparator.compare(ptr.getElement(), element) == 0)
					return i;
			} else {
				if ((ptr.getElement()).compareTo(element) == 0)
					return i;
			}
		}

		return -1;
	}

	/**
	 * @param index
	 * @return
	 */
	private Node<E> findPrevPtr(int index) {
		Node<E> nextPtr = head;
		for (int i = 0; i < (index - 1); i++) {
			nextPtr = nextPtr.getNextPtr();
		}

		return nextPtr;
	}

	/**
	 * Get the node one after the index point
	 * @param index
	 * @return nextPtr the node next to the index
	 */
	private Node<E> findNextPtr(int index) {
		Node<E> nextPtr = head;
		for (int i = 0; i <= index; i++) {
			nextPtr = nextPtr.getNextPtr();
		}

		return nextPtr;
	}

	/**
	 * @author chuck
	 *
	 * @param <E>
	 */
	private static class Node<E extends Comparable<E>> {
		private E value;
		private Node<E> nextPtr;
		private Node<E> prevPtr;

		public Node(E value, Node<E> nextPtr, Node<E> prevPtr) {
			this.value = value;
			this.nextPtr = nextPtr;
			this.prevPtr = prevPtr;
		}

		public Node<E> getNextPtr() {
			return this.nextPtr;
		}

		public void setNextPtr(Node<E> index) {
			this.nextPtr = index;
		}

		public Node<E> getPrevPtr() {
			return this.prevPtr;
		}

		public void setPrevPtr(Node<E> index) {
			this.prevPtr = index;
		}

		public E getElement() {
			return this.value;
		}

		public E setValue(E value) {
			E retVal = this.value;
			this.value = value;
			return retVal;
		}

		public String toString() {
			if (value == null)
				return "null";
			return value.toString();
		}
	}

	/**
	 * @author chuck
	 *
	 */
	private class ListLinkedInArrayIterator implements DsIterator<E> {
		Node<E> lastPtr; // lastPtr needed for remove
		Node<E> myPtr; // current pointer

		public ListLinkedInArrayIterator() {
			lastPtr = null;
			myPtr = head;
		}

		@Override
		public boolean hasNext() {
			if (myPtr == null)
				return false;
			else
				return true;
		}

		@Override
		public boolean hasPrevious() {
			if (myPtr == null) {
				return false;
			} else {
				return true;
			}
		}

		@Override
		public E next() {
			E retvar = (E) myPtr.getElement();

			lastPtr = myPtr;
			myPtr = myPtr.getNextPtr();
			return retvar;
		}

		@Override
		public E previous() {
			E retvar = (E) myPtr.getElement();

			lastPtr = myPtr;
			myPtr = myPtr.getPrevPtr();
			return retvar;
		}

		@Override
		public void setIteratorToStart() throws UnsupportedOperationException {
			myPtr = head;
		}

		@Override
		public void setIteratorToEnd() {
			myPtr = tail;
		}

		@Override
		public void remove() {
			if (lastPtr == null)
				myPtr = null;
			else {
				lastPtr.setNextPtr(myPtr.getNextPtr());
				myPtr = lastPtr;
				numElements = numElements - 1;
			}
		}
	}
}
