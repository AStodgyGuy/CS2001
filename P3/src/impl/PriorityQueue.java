package impl;

import common.QueueEmptyException;
import common.QueueFullException;
import interfaces.IPriorityQueue;

/**
 * Simple PriorityQueue implementation of the IPriorityQueue interface.
 */
public class PriorityQueue implements IPriorityQueue {

    private int maxSize;
    private int queueSize = 0;
    private QueueNode first;
    private QueueNode last;

    /**
     * Public constructor for Priority Queue.
     *
     * @param maxSize the maximum size of the PriorityQueue
     */
    public PriorityQueue(int maxSize) {
        this.maxSize = maxSize;
    }

    /**
     * Adds an element to the queue.
     *
     * @param element the element to be queued
     * @throws QueueFullException if there is no room in the queue for the new element
     */
    @Override
    public void enqueue(Comparable element) throws QueueFullException {

        if (queueSize + 1 <= maxSize) {
            QueueNode newNode = new QueueNode(element);
            if (isEmpty()) {
                first = newNode;
                last = newNode;
            } else {
                //while loop to determine the position of the new element in the queue according to its priority
                QueueNode elementInQueue = first;
                QueueNode nodeBefore = null;
                try {
                    //priority is greater
                    while (true) {
                        if (element == null) {
                            QueueNode oldLast = last;
                            oldLast.setNext(newNode);
                            last = newNode;
                            break;
                        }
                        if (elementInQueue.getElement() == null || newNode.getElement().compareTo(elementInQueue.getElement()) > 0) {
                            if (nodeBefore == null) {
                                first = newNode;
                                newNode.setNext(elementInQueue);
                            } else {
                                nodeBefore.setNext(newNode);
                                newNode.setNext(elementInQueue);
                            }
                            break;
                        }
                        nodeBefore = elementInQueue;
                        elementInQueue = elementInQueue.getNext();

                        //condition when loop reaches end of queue
                        if (elementInQueue == null) {
                            QueueNode oldLast = last;
                            oldLast.setNext(newNode);
                            last = newNode;
                            break;
                        }

                    }
                } catch (ClassCastException e) {
                    //potential to add other error handling things here
                    throw new ClassCastException();
                }
            }

            queueSize++;

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
        if (!isEmpty()) {
            QueueNode firstReference = first;
            first = first.getNext();
            queueSize--;
            return firstReference.getElement();
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
        first = null;
        queueSize = 0;
    }

    /**
     * Private inner class to represent a nodes in a queue.
     */
    private class QueueNode {
        private Comparable element;
        private QueueNode next;

        QueueNode(Comparable element) {
            this.element = element;
        }

        Comparable getElement() {
            return element;
        }

        void setNext(QueueNode next) {
            this.next = next;
        }

        QueueNode getNext() {
            return next;
        }
    }
}
