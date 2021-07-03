package com.infix;

/**
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


/**
 * This class defines the common methods for all
 * classes that are part of the Collection framework.
 *
 * @author  Charles Kann
 *
 * @param <E> the type of elements to be iterated.
 */

/*
 * Modification history
 */
public interface Collection<E extends Comparable<E>> extends DsIterable <E>, DataStructure<E> {
    /**
     * retrieve the number of elements in the collection.
     *
     * @return number of elements in the collection.
     */
    public int size();

    /**
     * retrieve a DsIterator for the elements
     *
     * @return a DsIterator for the elements
     */
    public DsIterator<E> iterator();

    /**
     * check if the collection is empty
     *
     * @return true if the collection is empty,
     * else false.
     */
    public boolean isEmpty();

    /**
     * clear the Collection by removing all elements.
     */
    public void clear();

    /**
     * retrieve an array of the elements in the Collection
     * returned as an Array of elements of type Object
     * See (https://www.geeksforgeeks.org/arraylist-array-conversion-java-toarray-methods/)
     *
     * @return an array of elements in the list
     *
     *  Note that this method returns an array of Objects, not the generic E.  This is for
     *  backward compatibility.
     */
    default public Object[] toArray() {
        Object[] o = new Object[this.size()];
        int i = 0;
        for (E element:this) {
            o[i] = element;
            i = i + 1;
        }

        return o;
    }

    /**
     * retrieve an array of the elements in the Collection
     * returned as an Array of elements of type E
     * Note: retArray must be the same size as the Collection
     * See (https://www.geeksforgeeks.org/arraylist-array-conversion-java-toarray-methods/)
     *
     * @return an array of elements in the list
     */

    default public E[] toArray(E[] retArray){
        if (retArray.length < this.size()) {
            throw new ArrayIndexOutOfBoundsException("return array must be the same or greater size as the Collection");
        }

        int i = 0;
        for (E element:this){
            retArray[i] = element;
            i = i + 1;
        }

        if (retArray.length > this.size())
            retArray[size()] = null;

        return retArray;
    }

    /**
     * Retrieve a string that lists the items in the collection
     *
     * @return a string containing the elements in the collection
     */
    default public String stringify(){
        String returnString = "";
        boolean firsttime = true;

        returnString = returnString + "[";
        for (E element: this){
            if (firsttime){
                firsttime = false;
            }
            else{
                returnString = returnString + ", ";
            }
            returnString = returnString + element;
        }
        returnString = returnString + "]";

        return returnString;
    }

    /**
     * @return a string designed to help debug the collection
     */
    default public String debug(){
        String returnString = "";
        boolean firsttime = true;
        for (E element: this){
            if (firsttime){
                firsttime = false;
            }
            else{
                returnString = returnString + ", ";
            }
            returnString = returnString + element;
        }
        return returnString;
    }
}
