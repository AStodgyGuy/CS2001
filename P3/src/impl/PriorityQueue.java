package impl;

import common.QueueEmptyException;
import common.QueueFullException;
import interfaces.IPriorityQueue;

/**
 * Simple PriorityQueue implementation of the IPriorityQueue interface.
 */
public class PriorityQueue implements IPriorityQueue {

    private int maxSize;
    private Comparable[] queueArray;
    private int queueSize = 0;
    private int queueHead = 0;
    /**
     * Public constructor for Priority Queue.
     *
     * @param maxSize the maximum size of the PriorityQueue
     */
    public PriorityQueue(int maxSize) {
        this.maxSize = maxSize;
        queueArray = new Comparable[maxSize];
    }

    /**
     * Adds an element to the queue.
     *
     * @param element the element to be queued
     * @throws QueueFullException if there is no room in the queue for the new element
     */
    @Override
    public void enqueue(Comparable element) throws QueueFullException {

        //check for max size of queue
        if (queueSize != maxSize) {
            //first element into the queue
            if (isEmpty()) {
                queueArray[queueHead] = element;
                queueSize++;

            //all other elements
            } else {
                if (element == null) {
                    queueArray[queueSize] = element;
                    queueSize++;
                } else {
                    boolean flag = true;
                    Comparable oldElement = null;
                    for (int i = queueHead; i <= queueSize; i++) {
                        //flag to determine when to shift all elements
                        if (flag) {
                            //priority is greater
                            if (queueArray[i] == null || element.compareTo(queueArray[i]) > 0) {
                                oldElement = queueArray[i];
                                queueArray[i] = element;
                                flag = false;
                            }

                        //shifting elements after adding the greater priority one in
                        } else {
                            Comparable oldElementRef = queueArray[i];
                            queueArray[i] = oldElement;
                            oldElement = oldElementRef;
                        }
                    }
                    queueSize++;

                }
            }
        } else {
            throw new QueueFullException();
        }

    }

    /**
     * Removes the largest element.
     *
     * @return the element removed
     * @throws QueueEmptyException if the queue is empty
     */
    @Override
    public Comparable dequeue() throws QueueEmptyException {

        //check if queue is empty
        if (!isEmpty()) {
            //the result to return
            Comparable result = queueArray[queueHead];
            Comparable oldElement = null;
            Comparable oldElementRef;
            //shift all elements of the queue up one
            for (int i = queueSize - 1; i >= queueHead; i--) {
                oldElementRef = queueArray[i];
                queueArray[i] = oldElement;
                oldElement = oldElementRef;
            }
            queueSize--;
            return result;
        } else {
            throw new QueueEmptyException();
        }
    }

    /**
     * Returns the number of elements in the queue.
     *
     * @return the number of elements in the queue
     */
    @Override
    public int size() {
        return queueSize;
    }

    /**
     * Checks whether the queue is empty.
     *
     * @return true if the queue is empty
     */
    @Override
    public boolean isEmpty() {
        return (queueSize == 0);
    }

    /**
     * Removes all elements from the queue.
     */
    @Override
    public void clear() {
        queueArray = new Comparable[maxSize];
        queueSize = 0;
    }

}
