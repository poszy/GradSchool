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
 * The DsIterable interface ensures that all Data Structures
 * classes have the ability to return an DsIterator.  The
 * Ds prefix means Data Structures, and is done to make
 * sure it it not confused with the Iterable interface
 * in java.lang.
 * <p>
 * The Iterator and ListIterator classes in Java implemented
 * methods that were not wanted for this datastructures
 * package, specifically the ability to modify the
 * under laying data structure using methods like
 * add and remove on the iterator. The DsIterable and DsIterator
 * are created to work around them.
 * <p>
 * Note that DSIterable extends Iterable, so it can be used
 * in a foreach statement.
 *
 * @author Charles Kann
 *
 * @param <T> the type of elements to be iterated.
 */

/*
 *   Modification History
 */
public interface DsIterable<T> extends Iterable<T> {
    /**
     * Returns a DsIterator over a set of elements of type T.
     *
     * @return a DsIterator (which extends Iterator).
     */
    public DsIterator<T> iterator();
}
