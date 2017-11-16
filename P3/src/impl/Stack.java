package impl;

import common.StackEmptyException;
import common.StackOverflowException;
import interfaces.IStack;

/**
 * Simple Stack implementation of the IStack interface.
 */
public class Stack implements IStack {

    private boolean isFirstStack;
    private int positionInArray;
    private int stackSize = 0;
    private int stackHead = -1;
    private Object[] stackArray;
    private Object[] doubleStackArray;

    /**
     * Public constructor for the Stack.
     *
     * @param doubleStackArray the internal array of the DoubleStack class
     * @param isFirstStack a boolean flag which separates the two instances of stack
     */
    public Stack(Object[] doubleStackArray, boolean isFirstStack) {
        this.doubleStackArray = doubleStackArray;
        this.isFirstStack = isFirstStack;
        this.stackArray = new Object[doubleStackArray.length];
        if (isFirstStack) {
            positionInArray = 0;
        } else {
            positionInArray = doubleStackArray.length - 1;
        }
    }

    /**
     * Pushes an element onto the stack.
     *
     * @param element the element to be pushed
     * @throws StackOverflowException if there is no room on the stack for the new element
     */
    @Override
    public void push(Object element) throws StackOverflowException {

        //double stack length of 0 check
        if (doubleStackArray.length == 0 || positionInArray == doubleStackArray.length || positionInArray < 0) {
            throw new StackOverflowException();
        }

        if (doubleStackArray[positionInArray] == null) {
            stackSize++;
            stackArray[++stackHead] = element;
            doubleStackArray[positionInArray] = new Object();

            if (isFirstStack) {
                if (positionInArray + 1 < doubleStackArray.length) {
                    positionInArray++;
                }
            } else {
                if (positionInArray - 1 >= 0) {
                    positionInArray--;
                }
            }

        } else {
            throw new StackOverflowException();
        }
    }

    /**
     * Pops an element from the stack.
     *
     * @return the popped element
     * @throws StackEmptyException if the stack is empty
     */
    @Override
    public Object pop() throws StackEmptyException {
        //Double
        if (doubleStackArray.length > 0) {
            //check if the stack is empty
            if (stackSize > 0) {
                doubleStackArray[positionInArray] = null; //remove the object in the internal array
                Object result = stackArray[stackHead];
                stackArray[stackHead] = null;
                stackHead--;
                stackSize--;
                //change the pointer to reflect a removed object in the internal array
                if (isFirstStack) {
                    positionInArray--;
                } else {
                    positionInArray++;
                }

                return result;
            } else {
                throw new StackEmptyException();
            }
        } else {
            throw new StackEmptyException();
        }
    }

    /**
     * Accesses the top element on the stack without removing it.
     *
     * @return the top element
     * @throws StackEmptyException if the stack is empty
     */
    @Override
    public Object top() throws StackEmptyException {
        if (stackHead != -1) {
            return stackArray[stackHead];
        } else {
            throw new StackEmptyException();
        }
    }

    /**
     * Returns the number of elements on the stack.
     *
     * @return the number of elements on the stack
     */
    @Override
    public int size() {
        return stackSize;
    }

    /**
     * Checks whether the stack is empty.
     *
     * @return true if the stack is empty
     */
    @Override
    public boolean isEmpty() {
        return (stackSize == 0);
    }

    /**
     * Removes all elements from the stack and removes the stack elements from the DoubleStack array.
     */
    @Override
    public void clear() {
        stackArray = new Object[doubleStackArray.length];
        if (isFirstStack) {
            for (int i = positionInArray; i > -1; i--) {
                doubleStackArray[i] = null;
            }
            positionInArray = 0;
        } else {
            for (int i = positionInArray; i < doubleStackArray.length; i++) {
                doubleStackArray[i] = null;
            }
            positionInArray = doubleStackArray.length - 1;
        }

        stackSize = 0;
    }
}
