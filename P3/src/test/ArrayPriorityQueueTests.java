package test;

import common.AbstractFactoryClient;
import common.QueueEmptyException;
import common.QueueFullException;
import interfaces.IPriorityQueue;
import org.junit.Test;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

/**
 * Tests priority queue implementation.
 */
public class ArrayPriorityQueueTests extends AbstractFactoryClient {

    private static final int DEFAULT_MAX_SIZE = 10;

    /**
     * Tests that the factory constructs a non-null priority queue.
     */
    @Test
    public void factoryReturnsNonNullDoubleStackObject() {

        IPriorityQueue queue = getFactory().makePriorityQueue(DEFAULT_MAX_SIZE);
        assertTrue("Failure: IFactory.makePriorityQueue returns null, expected non-null IPriorityQueue object", queue != null);
    }

    /**
     * Tests the size method of the queue implementation.
     * @throws QueueFullException if the queue has reached max size
     */
    @Test
    public void testQueueSize() throws QueueFullException {
        IPriorityQueue queue = getFactory().makePriorityQueue(DEFAULT_MAX_SIZE);
        assertEquals(0, queue.size());
    }

    /**
     * Tests enqueue elements into the queue.
     * @throws QueueFullException if the queue has reached max size
     */
    @Test
    public void testEnqueuingElements() throws QueueFullException {
        IPriorityQueue queue = getFactory().makePriorityQueue(DEFAULT_MAX_SIZE);
        queue.enqueue(1);
        assertEquals(1, queue.size());
    }

    /**
     * Tests dequeue-ing elements of the queue.
     * @throws QueueFullException if the queue has reached max size
     * @throws QueueEmptyException if there are no elements in the queue to deque
     */
    @Test
    public void testDequeingElements() throws QueueFullException, QueueEmptyException {
        IPriorityQueue queue = getFactory().makePriorityQueue(DEFAULT_MAX_SIZE);
        queue.enqueue(1);
        queue.dequeue();
        assertEquals(0, queue.size());
    }

    /**
     * Tests the isEmpty() method.
     */
    @Test
    public void testIsEmpty() {
        IPriorityQueue queue = getFactory().makePriorityQueue(DEFAULT_MAX_SIZE);
        assertEquals(true, queue.isEmpty());
    }

    /**
     * Tests the clear() method.
     * @throws QueueFullException if the queue has reached max size
     */
    @Test
    public void testClear() throws QueueFullException {
        IPriorityQueue queue = getFactory().makePriorityQueue(DEFAULT_MAX_SIZE);
        queue.enqueue(1);
        queue.clear();
        assertEquals(0, queue.size());
    }

    /**
     * Tests the clear() method and then adds more elements into the queue.
     * @throws QueueFullException if the queue has reached max size
     */
    @Test
    public void testClearThenEnqueue() throws QueueFullException {
        IPriorityQueue queue = getFactory().makePriorityQueue(DEFAULT_MAX_SIZE);
        queue.enqueue(1);
        queue.clear();
        queue.enqueue(3);
        queue.enqueue(5);
        queue.enqueue(2);
        assertEquals(3, queue.size());
    }

    /**
     * Test which enqueues and then dequeues.
     * @throws QueueFullException if the queue has reached max size
     * @throws QueueEmptyException if there are no elements in the queue to deque
     */
    @Test
    public void testDequeueThenEnqueue() throws QueueFullException, QueueEmptyException {
        IPriorityQueue queue = getFactory().makePriorityQueue(DEFAULT_MAX_SIZE);
        queue.enqueue(3);
        queue.enqueue(5);
        assertEquals(5, queue.dequeue());
        queue.enqueue(10);
        queue.enqueue(4);
        queue.enqueue(2);
        queue.enqueue(8);
        assertEquals(5, queue.size());
        assertEquals(10, queue.dequeue());
    }

    /**
     * Tests the clear() method for the max size of the queue.
     * @throws QueueFullException if the queue has reached max size
     */
    @Test
    public void testClearMaxSize() throws QueueFullException {
        IPriorityQueue queue = getFactory().makePriorityQueue(DEFAULT_MAX_SIZE);
        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);
        queue.enqueue(4);
        queue.enqueue(5);
        queue.enqueue(6);
        queue.enqueue(7);
        queue.enqueue(8);
        queue.enqueue(9);
        queue.enqueue(10);
        queue.clear();
        assertEquals(0, queue.size());
    }

    /**
     * Test that fills the queue and then dequeues all of it.
     * @throws QueueFullException if the queue has reached max size
     * @throws QueueEmptyException if there are no elements to deque
     */
    @Test
    public void testAddMaxElementsThenDequeueAll() throws QueueFullException, QueueEmptyException {
        IPriorityQueue queue = getFactory().makePriorityQueue(DEFAULT_MAX_SIZE);
        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);
        queue.enqueue(4);
        queue.enqueue(5);
        queue.enqueue(6);
        queue.enqueue(7);
        queue.enqueue(8);
        queue.enqueue(9);
        queue.enqueue(10);
        assertEquals(10, queue.dequeue());
        assertEquals(9, queue.dequeue());
        assertEquals(8, queue.dequeue());
        assertEquals(7, queue.dequeue());
        assertEquals(6, queue.dequeue());
        assertEquals(5, queue.dequeue());
        assertEquals(4, queue.dequeue());
        assertEquals(3, queue.dequeue());
        assertEquals(2, queue.dequeue());
        assertEquals(1, queue.dequeue());
    }


    /**
     * Test the clear method and then deque method to guarantee that the queue is clear.
     * @throws QueueFullException if the queue has reached max size
     * @throws QueueEmptyException if there are no elements to deque
     */
    @Test (expected = QueueEmptyException.class)
    public void testClearThenDeque() throws QueueFullException, QueueEmptyException {
        IPriorityQueue queue = getFactory().makePriorityQueue(DEFAULT_MAX_SIZE);
        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);
        queue.enqueue(4);
        queue.enqueue(5);
        queue.enqueue(6);
        queue.enqueue(7);
        queue.clear();
        queue.dequeue();
    }

    /**
     * Test for max size of queue.
     * @throws QueueFullException if the queue has reached max size
     */
    @Test(expected = QueueFullException.class)
    public void testMaxSize() throws QueueFullException {
        IPriorityQueue queue = getFactory().makePriorityQueue(DEFAULT_MAX_SIZE);
        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);
        queue.enqueue(4);
        queue.enqueue(5);
        queue.enqueue(6);
        queue.enqueue(7);
        queue.enqueue(8);
        queue.enqueue(9);
        queue.enqueue(10);
        queue.enqueue(11);
    }

    /**
     * Tests for dequeing an empty queue.
     * @throws QueueEmptyException if there are no elements in the queue to deque
     */
    @Test (expected = QueueEmptyException.class)
    public void testDequeEmptyQue() throws QueueEmptyException {
        IPriorityQueue queue = getFactory().makePriorityQueue(DEFAULT_MAX_SIZE);
        queue.dequeue();
    }

    /**
     * Test for one enqueue and then two deque.
     * @throws QueueFullException if the queue has reached max size
     * @throws QueueEmptyException if there are no elements in the queue to deque
     */
    @Test (expected = QueueEmptyException.class)
    public void testOneEnqueueTwoDeque() throws QueueEmptyException, QueueFullException {
        IPriorityQueue queue = getFactory().makePriorityQueue(DEFAULT_MAX_SIZE);
        queue.enqueue(1);
        queue.dequeue();
        queue.dequeue();
    }

    /**
     * Test for priority when adding elements into queue.
     * @throws QueueFullException if the queue has reached max size
     * @throws QueueEmptyException if there are no elements in the queue to deque
     */
    @Test
    public void testAddingPriority() throws QueueFullException, QueueEmptyException {
        IPriorityQueue queue = getFactory().makePriorityQueue(DEFAULT_MAX_SIZE);
        queue.enqueue(1);
        queue.enqueue(5);
        assertEquals(5, queue.dequeue());
    }

    /**
     * Tests adding random priority to the queue.
     * @throws QueueFullException if the queue has reached max size
     * @throws QueueEmptyException if there are no elements in the queue to deque
     */
    @Test
    public void testAddingRandomPriority() throws QueueFullException, QueueEmptyException {
        IPriorityQueue queue = getFactory().makePriorityQueue(DEFAULT_MAX_SIZE);
        queue.enqueue(4);
        queue.enqueue(1);
        queue.enqueue(9);
        queue.enqueue(2);
        queue.enqueue(5);
        assertEquals(9, queue.dequeue());
    }

    /**
     * Tests adding random priority to the queue and then dequeing them 1 by 1.
     * @throws QueueFullException if the queue has reached max size
     * @throws QueueEmptyException if there are no elements in the queue to deque
     */
    @Test
    public void testAddingRandomPriorityAndThenDequeAll() throws QueueFullException, QueueEmptyException {
        IPriorityQueue queue = getFactory().makePriorityQueue(DEFAULT_MAX_SIZE);
        queue.enqueue(5);
        queue.enqueue(6);
        queue.enqueue(11);
        queue.enqueue(7);
        assertEquals(11, queue.dequeue());
        assertEquals(7, queue.dequeue());
        assertEquals(6, queue.dequeue());
        assertEquals(5, queue.dequeue());
    }

    /**
     * Tests adding equal priority elements to the queue and since they have equal priority, the first element added into the queue should be the first element de-queued.
     * @throws QueueFullException if the queue is full
     * @throws QueueEmptyException if there are no elements in the que to deque
     */
    @Test
    public void testAddingElementsOfSamePriority() throws QueueFullException, QueueEmptyException {
        IPriorityQueue queue = getFactory().makePriorityQueue(DEFAULT_MAX_SIZE);
        TestObject object1 = new TestObject(4, "first");
        TestObject object2 = new TestObject(4, "second");
        TestObject object3 = new TestObject(4, "third");
        queue.enqueue(object1);
        queue.enqueue(object2);
        queue.enqueue(object3);
        TestObject dequeuedObject = (TestObject) queue.dequeue();
        assertEquals("first", dequeuedObject.getInsertionValue());
    }

    /**
     * Test adding uncomparable objects, method throws a class cast exception.
     * @throws QueueFullException if the queue is full
     */
    @Test (expected = ClassCastException.class)
    public void testAddingUncomparableObjects() throws QueueFullException {
        IPriorityQueue queue = getFactory().makePriorityQueue(DEFAULT_MAX_SIZE);
        queue.enqueue(4);
        queue.enqueue(1);
        queue.enqueue(9);
        queue.enqueue(2);
        queue.enqueue("Jon Lewis");
    }

    /**
     * Test adding null objects into the queue.
     * @throws QueueFullException if there are no elements in the que to deque
     */
    @Test
    public void testAddingNullObjects() throws QueueFullException {
        IPriorityQueue queue = getFactory().makePriorityQueue(DEFAULT_MAX_SIZE);
        queue.enqueue(null);
        queue.enqueue(null);
        assertEquals(2, queue.size());
    }

    /**
     * Test removing null objects that were put into the queue.
     * @throws QueueFullException if the queue is full
     * @throws QueueEmptyException if there are no elements in the que to deque
     */
    @Test
    public void testRemovingNullObjects() throws QueueFullException, QueueEmptyException {
        IPriorityQueue queue = getFactory().makePriorityQueue(DEFAULT_MAX_SIZE);
        queue.enqueue(null);
        queue.enqueue(null);
        queue.enqueue(null);
        queue.enqueue(null);
        assertEquals(null, queue.dequeue());
        assertEquals(3, queue.size());
    }

    /**
     * Test which puts null and integers in the same queue.
     * @throws QueueFullException if the queue is full
     * @throws QueueEmptyException if there are no elements in the que to deque
     */
    @Test
    public void testAddNullAndInteger() throws QueueFullException, QueueEmptyException {
        IPriorityQueue queue = getFactory().makePriorityQueue(DEFAULT_MAX_SIZE);
        queue.enqueue(8);
        queue.enqueue(9);
        queue.enqueue(null);
        queue.enqueue(13);
        queue.enqueue(14);
        queue.enqueue(null);
        queue.enqueue(10);
        assertEquals(7, queue.size());
        assertEquals(14, queue.dequeue());
        assertEquals(13, queue.dequeue());
        assertEquals(10, queue.dequeue());
    }

    /**
     * Test which puts null, integers and strings in the same queue test expected to throw ClassCastException.
     * @throws QueueFullException if the queue is full
     * @throws QueueEmptyException if there are no elements in the que to deque
     */
    @Test (expected = ClassCastException.class)
    public void testAddNullAndIntegerAndString() throws QueueFullException, QueueEmptyException {
        IPriorityQueue queue = getFactory().makePriorityQueue(DEFAULT_MAX_SIZE);
        queue.enqueue(8);
        queue.enqueue(9);
        queue.enqueue(null);
        queue.enqueue("hello");
        queue.enqueue(null);
        queue.enqueue(10);
    }

    /**
     * Test which puts null and integers inside a queue and deques all elements.
     * @throws QueueFullException if the queue is full
     * @throws QueueEmptyException if there are no elements in the que to deque
     */
    @Test
    public void testAddNullAndIntegerThenDequeAll() throws QueueFullException, QueueEmptyException {
        IPriorityQueue queue = getFactory().makePriorityQueue(DEFAULT_MAX_SIZE);
        queue.enqueue(8);
        queue.enqueue(9);
        queue.enqueue(null);
        queue.enqueue(13);
        queue.enqueue(14);
        queue.enqueue(null);
        queue.enqueue(10);
        assertEquals(14, queue.dequeue());
        assertEquals(13, queue.dequeue());
        assertEquals(10, queue.dequeue());
        assertEquals(9, queue.dequeue());
        assertEquals(8, queue.dequeue());
        assertEquals(null, queue.dequeue());
        assertEquals(null, queue.dequeue());
    }

    /**
     * Test that puts TestObjects with a value of null inside a queue and dequeues them.
     * @throws QueueFullException if the queue is full
     * @throws QueueEmptyException if there are no elements in the que to deque
     */
    @Test
    public void testNullFieldInObject() throws QueueFullException, QueueEmptyException {
        IPriorityQueue queue = getFactory().makePriorityQueue(DEFAULT_MAX_SIZE);
        TestObject object1 = new TestObject("first");
        TestObject object2 = new TestObject("second");
        TestObject object3 = new TestObject("third");
        queue.enqueue(object1);
        queue.enqueue(object2);
        queue.enqueue(object3);
        TestObject dequeuedObject = (TestObject) queue.dequeue();
        assertEquals("first", dequeuedObject.getInsertionValue());
        dequeuedObject = (TestObject) queue.dequeue();
        assertEquals("second", dequeuedObject.getInsertionValue());
        dequeuedObject = (TestObject) queue.dequeue();
        assertEquals("third", dequeuedObject.getInsertionValue());
    }


    /**
     * Class built for testing ordering of objects.
     */
    private class TestObject implements Comparable<TestObject> {

        private int value;
        private String insertionValue;

        TestObject(int value, String insertionValue) {
            this.value = value;
            this.insertionValue = insertionValue;
        }

        TestObject(String insertionValue) {
            this.insertionValue = insertionValue;
        }

        int getValue() {
            return value;
        }

        String getInsertionValue() {
            return insertionValue;
        }

        /**
         * Method which compares one test object to another.
         * @param other the other object to compare to
         * @return the integer value whether one is greater than, equal to or less than the object
         */
        @Override
        public int compareTo(TestObject other) {
            return Integer.compare(this.getValue(), other.getValue());
        }
    }

}
