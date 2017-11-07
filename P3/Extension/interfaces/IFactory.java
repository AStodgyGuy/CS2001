package interfaces;


/**
 * Interface for a factory allowing the other interfaces to be instantiated without knowing the implementation classes.
 *
 */
public interface IFactory {

    /**
     * Creates an instance of {@link IDoubleStack}.
     * @param maxSize the maximum size of the stack
     * @return the double stack
     */
    IDoubleStack makeDoubleStack(int maxSize);


    /**
     * This method creates an instance of {@link IPriorityQueue}.
     * @return the priority queue
     */
    IPriorityQueue makePriorityQueue();


}
