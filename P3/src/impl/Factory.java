package impl;

import interfaces.IDoubleStack;
import interfaces.IFactory;
import interfaces.IPriorityQueue;

/**
 * This class implements a singleton factory.
 *
 */
public final class Factory implements IFactory {

    private static IFactory factoryInstance = null;

    private Factory() {
    }

    /**
     * Method which returns an instance of the singleton Factory class.
     * @return the instance of the Factory
     */
    public static IFactory getInstance() {
        if (factoryInstance == null) {
            factoryInstance = new Factory();
        }
        return factoryInstance;
    }

    /**
     * Creates an instance of {@link IDoubleStack}.
     * @param maxSize the maximum size of the stack
     * @return the double stack
     */
    @Override
    public IDoubleStack makeDoubleStack(int maxSize) {
        return new DoubleStack(maxSize);
    }

    /**
     * This method creates an instance of {@link IPriorityQueue}.
     * @param maxSize the maximum size of queue
     * @return the priority queue
     */
    @Override
    public IPriorityQueue makePriorityQueue(int maxSize) {
        return new PriorityQueue(maxSize);
    }

}
