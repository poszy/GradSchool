package huffman;

/*
 * Copyright (c) 2015, Charles Kann. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License version 2 only, as
 * published by the Free Software Foundation.
 *
 * This code is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License
 * version 2 for more details (a copy is included in the LICENSE file that
 * accompanied this code).
 *
 * You should have received a copy of the GNU General Public License version
 * 2 along with this work; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 *
 * Please contact ckann@gettysburg.edu if you need additional information
 * or have any questions.
 */
import java.util.Comparator;

/**
 * A IndexedList is an index ordered Collection.  It is accessed by index like an array.
 *
 * @author Charles Kann
 *
 * @param <E> The elements to be stored in the list.  The
 * elements must be Comparable because the contains and
 * remove operations will use the Comparable interface
 * compare method to check for equality
 */
public interface IndexedList<E extends Comparable<E>> extends List<E> {

    /**
     * add an element to the end of the IndexedList.
     *
     * @param element the element to be stored.
     */

    public void printReverse();
    public void add(E element);

    /**
     * add an element to the IndexedList at the specified index. All elements in the list,
     * including the one at index, are moved forward one slot.
     *
     * @param index the point at which to store the element.  This
     * is 0 based (like an array).  So a call add(0, element) will
     * insert the item as the item as the 0 element.
    .
     *
     * @param element the element to be stored.
     */
    public void add(int index, E element);

    /**
     * add a Collection of elements to the end of the IndexedList.
     *
     * @param elements The collection of elements of type E
     *                 to add to the Bag
     */
    public void add(Collection<E> elements) throws DataStructureException;

    /**
     * add the collection of elements at the specified index.  All elements in the
     * list, including the one at index, are moved forward elements.size() places.
     *
     * @param index the point at which to store the element.  This
     * is 0 based (like an array).  So a call add(0, element) will
     * insert the item as the item as the 0 element.

     * @param elements the elements to be stored.
     */
    public void add(int index, Collection<E> elements)throws DataStructureException;

    /**
     * add a array of elements to the end of the IndexedList.
     *
     * @param elements the elements to be stored.
     */
    public void add(E[] elements);

    /**
     * add an array of elements to the array at the specified index. All elements in the
     * list, including the one at index, are moved forward elements.length places.
     *
     * @param index the point at which to store the element.This
     * is 0 based (like an array).  So a call add(0, element) will
     * insert the item as the item as the 0 element.

     * @param elements the elements to be stored.
     */
    public void add(int index, E[] elements);


    /**
     * return the index of the first list element that matches the parameter element,
     * or -1 if it is not matched.  The natural sort order is used to compare elements.
     *
     * @param element the element value to look for
     * @return the index of the first occurrence of the
     * element in the list.
     */
    public int indexOf(E element);

    /**
     * return the index of the first list element that matches the parameter element,
     * or -1 if it is not matched.  The comparator is used to compare elements.
     *
     * @param element the element value to look for
     * @param comparator a comparator to use to find the item
     * @return the index  of the first occurrence of the
     * element in the list.
     */
    public int indexOf(E element, Comparator<E> comparator);

    /**
     * return the index of the first list element that matches the parameter element,
     * or -1 if it is not matched.  The natural sort order is used to compare elements.
     * The search begins are index.
     *
     * @param element the element value to look for
     * @return the index of the first occurrence of the element in the list.
     */
    public int indexOf(int index, E element);

    /**
     * return the index of the first list element that matches the parameter element,
     * or -1 if it is not matched.  The comparator is used to compare elements.
     * The search begins are index.
     *
     * @param element the element value to look for
     * @return the index of the first occurrence of the element in the list.
     */
    public int indexOf(int index, E element, Comparator<E> comparator);

    /**
     * return the index of the last list element that matches the parameter
     * element, or -1 if it is not matched.  The natural sort order
     * is used.
     *
     * @param element the element value to look for
     * @return the index  of the last occurrence of the
     * element in the list.
     */

    public int lastIndexOf(E element);

    /**
     * return the index of the last list element that matches the parameter
     * element, or -1 if it is not matched.  The  comparator is used
     * to compare elements.
     *
     * @param element the element value to look for
     * @param comparator a comparator to use to find the item
     * @return the index  of the last occurrence of the
     * element in the list.
     */
    public int lastIndexOf(E element, Comparator<E> comparator);

    /**
     * removes the element at index
     *
     * @param index the index of the element to remove
     * @return the element which was removed
     */
    public E remove(int index);

    /**
     * retrieve the element at this index
     *
     * @param index the index of the element to retrieve
     * @return the element at index
     */
    public E retrieve(int index);

    /**
     * changes the element stored at index
     *
     * @param index the index of the element to change
     * @param element the new element to store
     * @return the original element stored at index
     */
    public E set(int index, E element);

}
