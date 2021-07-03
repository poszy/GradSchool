package com.infix;
/*
 * @(#)SimpleList.java 2013/01/4
 * @author Charles W. Kann III
 *
 * Permission to use, copy, modify, and distribute this
 * software and its documentation for NON-COMMERCIAL purposes
 * and without fee is hereby granted provided that this
 * copyright notice appears in all copies.
 *
 * THE AUTHOR MAKES NO REPRESENTATIONS OR WARRANTIES ABOUT THE
 * SUITABILITY OF THE SOFTWARE, EITHER EXPRESS OR IMPLIED,
 * INCLUDING BUT NOT LIMITED TO THE IMPLIED WARRANTIES OF
 * MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE, OR
 * NON-INFRINGEMENT. THE AUTHOR SHALL NOT BE LIABLE FOR ANY DAMAGES
 * SUFFERED BY LICENSEE AS A RESULT OF USING, MODIFYING OR
 * DISTRIBUTING THIS SOFTWARE OR ITS DERIVATIVES.
 */


/**
 *  Purpose: This is an interface definition for a IndexedList
*/

public interface Stack<E extends Comparable <E>> extends Collection<E> {
    /*
        pushes to item E to the end of the stack
        
        @param  E The item to push on the stack.
        
        side effects: E is added at the end of the stack.
    */
    public void push(E element);

    /*
       retrieves and removes the item at the end of the stack.

       @return the element at the end of the stack.

       side effects: The element at the end of the stack is removed.
       
    */
    public E pop();
    
    /*
       retrieves the item at the end of the stack.

       @return the element at the end of the stack.

       side effects: None.  The stack is unchanged.
       
    */
    public E peek();
}
