package com.infix;

public interface Queue<E extends Comparable<E>> extends Collection<E> {

	public void enqueue(E element);
	public E dequeue();
	public E peek();
}
