package impl;


import interfaces.IDoubleStack;
import interfaces.IStack;

/**
 * Simple DoubleStack implementation of the IDoubleStack interface.
 */
public class DoubleStack implements IDoubleStack {


    private Stack firstStack;
    private Stack secondStack;
    private Object[] array;


    /**
     * Public constructor for DoubleStack.
     *
     * @param maxSize the maximum size of the DoubleStack
     */
    public DoubleStack(int maxSize) {
        array = new Object[maxSize];
        firstStack = new Stack(array, true);
        secondStack = new Stack(array, false);
    }


    /**
     * Method which returns the first IStack object in the IDoubleStack for subsequent use with {@link IStack} operations.
     *
     * @return the first stack in the double stack
     */
    @Override
    public IStack getFirstStack() {
        return firstStack;
    }

    /**
     * Method which returns the second IStack in the IDoubleStack object for subsequent use with {@link IStack} operations.
     *
     * @return the first stack in the double stack
     */
    @Override
    public IStack getSecondStack() {
        return secondStack;
    }
}
