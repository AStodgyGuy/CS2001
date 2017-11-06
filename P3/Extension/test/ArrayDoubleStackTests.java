package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import common.StackEmptyException;
import common.StackOverflowException;
import org.junit.Test;

import common.AbstractFactoryClient;
import interfaces.IDoubleStack;

import java.util.EmptyStackException;

/**
 * Tests array collection implementation.
 */
public class ArrayDoubleStackTests extends AbstractFactoryClient {

    private static final int DEFAULT_MAX_SIZE = 10;

    /**
     * Tests that the factory constructs a non-null double stack.
     */
    @Test
    public void factoryReturnsNonNullDoubleStackObject() {

        IDoubleStack doubleStack1 = getFactory().makeDoubleStack(DEFAULT_MAX_SIZE);
        assertTrue("Failure: IFactory.makeDoubleStack returns null, expected non-null IDoubleStack object", doubleStack1 != null);
    }

    /**
     * Tests the creation of the first stack in the DoubleStack object.
     */
    @Test
    public void testFirstStack() {
        IDoubleStack doubleStack1 = getFactory().makeDoubleStack(DEFAULT_MAX_SIZE);
        assertNotNull(doubleStack1.getFirstStack());
    }

    /**
     * Tests the creation of the second stack in the DoubleStack object.
     */
    @Test
    public void testSecondStack() {
        IDoubleStack doubleStack1 = getFactory().makeDoubleStack(DEFAULT_MAX_SIZE);
        assertNotNull(doubleStack1.getSecondStack());
    }

    /**
     * Tests the push method in first stack.
     * @throws StackOverflowException if the stack has no space left
     */
    @Test
    public void testPushObjectInFirstStack() throws StackOverflowException {
        IDoubleStack doubleStack1 = getFactory().makeDoubleStack(DEFAULT_MAX_SIZE);
        doubleStack1.getFirstStack().push(1);
        doubleStack1.getFirstStack().push(2);
        doubleStack1.getFirstStack().push(3);
        doubleStack1.getFirstStack().push(4);
        doubleStack1.getFirstStack().push(5);
        doubleStack1.getFirstStack().push(6);
        doubleStack1.getFirstStack().push(7);
        assertEquals(7, doubleStack1.getFirstStack().size());
    }

    /**
     * Tests the push method in second stack.
     * @throws StackOverflowException if the stack has no space left
     */
    @Test
    public void testPushObjectInSecondStack() throws StackOverflowException {
        IDoubleStack doubleStack1 = getFactory().makeDoubleStack(DEFAULT_MAX_SIZE);
        doubleStack1.getSecondStack().push(1);
        doubleStack1.getSecondStack().push(2);
        doubleStack1.getSecondStack().push(3);
        doubleStack1.getSecondStack().push(4);
        doubleStack1.getSecondStack().push(5);
        doubleStack1.getSecondStack().push(6);
        doubleStack1.getSecondStack().push(7);
        assertEquals(7, doubleStack1.getSecondStack().size());
    }

    /**
     * Test which pushes items into the two stacks at separate points.
     * @throws StackOverflowException if the stack has no space left
     */
    @Test
    public void testPushObjectsIntoBothStacks() throws StackOverflowException {
        IDoubleStack doubleStack1 = getFactory().makeDoubleStack(DEFAULT_MAX_SIZE);
        doubleStack1.getFirstStack().push(1);
        doubleStack1.getFirstStack().push(2);
        doubleStack1.getFirstStack().push(3);
        doubleStack1.getSecondStack().push(4);
        doubleStack1.getSecondStack().push(5);
        doubleStack1.getSecondStack().push(6);
        assertEquals(3, doubleStack1.getFirstStack().size());
        assertEquals(3, doubleStack1.getSecondStack().size());
    }

    /**
     * Test which tests the DoubleStack limit.
     * @throws StackOverflowException if the stack has no more space left
     */
    @Test (expected = StackOverflowException.class)
    public void testMaxOutDoubleStack() throws StackOverflowException {
        IDoubleStack doubleStack1 = getFactory().makeDoubleStack(DEFAULT_MAX_SIZE);
        doubleStack1.getFirstStack().push(0);
        doubleStack1.getFirstStack().push(1);
        doubleStack1.getFirstStack().push(2);
        doubleStack1.getFirstStack().push(3);
        doubleStack1.getSecondStack().push(4);
        doubleStack1.getSecondStack().push(5);
        doubleStack1.getSecondStack().push(6);
        doubleStack1.getSecondStack().push(7);
        doubleStack1.getSecondStack().push(8);
        doubleStack1.getSecondStack().push(9);
        assertEquals(4, doubleStack1.getFirstStack().size());
        assertEquals(6, doubleStack1.getSecondStack().size());
        doubleStack1.getSecondStack().push(10);
    }

    /**
     * Test the pop method for stack 1.
     * @throws StackOverflowException if the stack has no more space left
     * @throws StackEmptyException if the stack is empty
     */
    @Test
    public void testFirstStackPop() throws StackOverflowException, StackEmptyException {
        IDoubleStack doubleStack1 = getFactory().makeDoubleStack(DEFAULT_MAX_SIZE);
        doubleStack1.getFirstStack().push(0);
        doubleStack1.getFirstStack().push(1);
        doubleStack1.getFirstStack().push(2);
        doubleStack1.getFirstStack().push(3);
        assertEquals(3,doubleStack1.getFirstStack().pop());
        assertEquals(2,doubleStack1.getFirstStack().pop());
        assertEquals(1,doubleStack1.getFirstStack().pop());
        assertEquals(0,doubleStack1.getFirstStack().pop());
    }

    /**
     * Test the pop method for stack 2.
     * @throws StackOverflowException if the stack has no more space left
     * @throws StackEmptyException if the stack is empty
     */
    @Test
    public void testSecondStackPop() throws StackOverflowException, StackEmptyException {
        IDoubleStack doubleStack1 = getFactory().makeDoubleStack(DEFAULT_MAX_SIZE);
        doubleStack1.getSecondStack().push(0);
        doubleStack1.getSecondStack().push(1);
        doubleStack1.getSecondStack().push(2);
        doubleStack1.getSecondStack().push(3);
        assertEquals(3,doubleStack1.getSecondStack().pop());
        assertEquals(2,doubleStack1.getSecondStack().pop());
        assertEquals(1,doubleStack1.getSecondStack().pop());
        assertEquals(0,doubleStack1.getSecondStack().pop());
    }

    /**
     * Test the pop method for stack 1 and stack 2 when in the DoubleStack.
     * @throws StackOverflowException if the stack has no more space left
     * @throws StackEmptyException if the stack is empty
     */
    @Test
    public void testFirstAndSecondStackPopSimultaneously() throws StackOverflowException, StackEmptyException {
        IDoubleStack doubleStack1 = getFactory().makeDoubleStack(DEFAULT_MAX_SIZE);
        doubleStack1.getFirstStack().push(0);
        doubleStack1.getFirstStack().push(1);
        doubleStack1.getFirstStack().push(2);
        doubleStack1.getFirstStack().push(3);
        doubleStack1.getSecondStack().push(0);
        doubleStack1.getSecondStack().push(1);
        doubleStack1.getSecondStack().push(2);
        doubleStack1.getSecondStack().push(3);
        assertEquals(3,doubleStack1.getFirstStack().pop());
        assertEquals(2,doubleStack1.getFirstStack().pop());
        assertEquals(1,doubleStack1.getFirstStack().pop());
        assertEquals(0,doubleStack1.getFirstStack().pop());
        assertEquals(3,doubleStack1.getSecondStack().pop());
        assertEquals(2,doubleStack1.getSecondStack().pop());
        assertEquals(1,doubleStack1.getSecondStack().pop());
        assertEquals(0,doubleStack1.getSecondStack().pop());
    }

    /**
     * Test which tries to pop an empty stack, test expected to run into an exception.
     * @throws StackEmptyException if the stack is empty
     */
    @Test (expected = StackEmptyException.class)
    public void testFirstStackEmptyQueuePop() throws StackEmptyException {
        IDoubleStack doubleStack1 = getFactory().makeDoubleStack(DEFAULT_MAX_SIZE);
        doubleStack1.getFirstStack().pop();
    }

    /**
     * Test which tries to pop an element out of a stack twice, test expected to run into an exception.
     * @throws StackOverflowException if the stack has no more space left
     * @throws StackEmptyException if the stack is empty
     */
    @Test (expected = StackEmptyException.class)
    public void testFirstStackAddThenEmptyQueuePop() throws StackOverflowException, StackEmptyException {
        IDoubleStack doubleStack1 = getFactory().makeDoubleStack(DEFAULT_MAX_SIZE);
        doubleStack1.getFirstStack().push(0);
        doubleStack1.getFirstStack().pop();
        doubleStack1.getFirstStack().pop();
    }

    /**
     * Test which puts elements into both stacks and empties the second stack. The test contains an extra pop for the test to run into an exception.
     * @throws StackOverflowException if the stack has no more space left
     * @throws StackEmptyException if the stack is empty
     */
    @Test (expected = StackEmptyException.class)
    public void testDoubleStackEmptyQueuePop() throws StackOverflowException, StackEmptyException {
        IDoubleStack doubleStack1 = getFactory().makeDoubleStack(DEFAULT_MAX_SIZE);
        doubleStack1.getFirstStack().push(0);
        doubleStack1.getFirstStack().push(1);
        doubleStack1.getFirstStack().push(2);
        doubleStack1.getFirstStack().push(3);
        doubleStack1.getSecondStack().push(0);
        doubleStack1.getSecondStack().push(1);
        doubleStack1.getSecondStack().push(2);
        doubleStack1.getSecondStack().push(3);
        doubleStack1.getSecondStack().pop();
        doubleStack1.getSecondStack().pop();
        doubleStack1.getSecondStack().pop();
        doubleStack1.getSecondStack().pop();
        doubleStack1.getSecondStack().pop();
    }

    /**
     * Test which adds null elements into the stack.
     * @throws StackOverflowException if the stack has no more space left
     * @throws StackEmptyException if the stack is empty
     */
    @Test
    public void testAddingNullElements() throws StackOverflowException, StackEmptyException {
        IDoubleStack doubleStack1 = getFactory().makeDoubleStack(DEFAULT_MAX_SIZE);
        doubleStack1.getFirstStack().push(0);
        doubleStack1.getFirstStack().push(null);
        doubleStack1.getSecondStack().push(0);
        doubleStack1.getSecondStack().push(null);
        assertEquals(null, doubleStack1.getFirstStack().pop());
        assertEquals(0, doubleStack1.getFirstStack().pop());
        assertEquals(null, doubleStack1.getSecondStack().pop());
        assertEquals(0, doubleStack1.getSecondStack().pop());
    }

    /**
     * Test which adds null, integers and strings into a stack
     * @throws StackOverflowException if the stack has no more space left
     * @throws StackEmptyException if the stack is empty
     */
    @Test
    public void testAddingDifferentElements() throws StackOverflowException, StackEmptyException {
        IDoubleStack doubleStack1 = getFactory().makeDoubleStack(DEFAULT_MAX_SIZE);
        doubleStack1.getFirstStack().push(0);
        doubleStack1.getFirstStack().push(null);
        doubleStack1.getFirstStack().push("Lei Fang");
        doubleStack1.getSecondStack().push(0);
        doubleStack1.getSecondStack().push(null);
        doubleStack1.getSecondStack().push("Simon Dobson");
        assertEquals("Lei Fang", doubleStack1.getFirstStack().pop());
        assertEquals(null, doubleStack1.getFirstStack().pop());
        assertEquals(0, doubleStack1.getFirstStack().pop());
        assertEquals("Simon Dobson", doubleStack1.getSecondStack().pop());
        assertEquals(null, doubleStack1.getSecondStack().pop());
        assertEquals(0, doubleStack1.getSecondStack().pop());
    }

    /**
     * Test which fills stack one to the maximum size of the double stack and then adds an element into the second stack.
     * Test expected to run into a StackOverFlow exception.
     * @throws StackOverflowException if the stack has no more space left
     */
    @Test (expected = StackOverflowException.class)
    public void testMaxOutStackOne() throws StackOverflowException {
        IDoubleStack doubleStack1 = getFactory().makeDoubleStack(DEFAULT_MAX_SIZE);
        doubleStack1.getFirstStack().push(0);
        doubleStack1.getFirstStack().push(1);
        doubleStack1.getFirstStack().push(2);
        doubleStack1.getFirstStack().push(3);
        doubleStack1.getFirstStack().push(4);
        doubleStack1.getFirstStack().push(5);
        doubleStack1.getFirstStack().push(6);
        doubleStack1.getFirstStack().push(7);
        doubleStack1.getFirstStack().push(8);
        doubleStack1.getFirstStack().push(9);
        doubleStack1.getSecondStack().push("an element");
    }

    /**
     * Same test as above only this is for stack two rather than stack one.
     * @throws StackOverflowException if the stack has no more space left
     */
    @Test (expected = StackOverflowException.class)
    public void testMaxOutStackTwo() throws StackOverflowException {
        IDoubleStack doubleStack1 = getFactory().makeDoubleStack(DEFAULT_MAX_SIZE);
        doubleStack1.getSecondStack().push(0);
        doubleStack1.getSecondStack().push(1);
        doubleStack1.getSecondStack().push(2);
        doubleStack1.getSecondStack().push(3);
        doubleStack1.getSecondStack().push(4);
        doubleStack1.getSecondStack().push(5);
        doubleStack1.getSecondStack().push(6);
        doubleStack1.getSecondStack().push(7);
        doubleStack1.getSecondStack().push(8);
        doubleStack1.getSecondStack().push(9);
        doubleStack1.getFirstStack().push("an element");
    }

    /**
     * Test the isEmpty() method.
     */
    @Test
    public void testIsEmpty() {
        IDoubleStack doubleStack1 = getFactory().makeDoubleStack(DEFAULT_MAX_SIZE);
        assertEquals(true, doubleStack1.getFirstStack().isEmpty());
        assertEquals(true, doubleStack1.getSecondStack().isEmpty());
    }

    /**
     * Test the clear() method
     * @throws StackOverflowException if the stack has no more space left
     */
    @Test
    public void testClear() throws StackOverflowException {
        IDoubleStack doubleStack1 = getFactory().makeDoubleStack(DEFAULT_MAX_SIZE);
        doubleStack1.getFirstStack().push(0);
        doubleStack1.getFirstStack().push(1);
        doubleStack1.getFirstStack().push(2);
        doubleStack1.getFirstStack().push(3);
        doubleStack1.getSecondStack().push(0);
        doubleStack1.getSecondStack().push(1);
        doubleStack1.getSecondStack().push(2);
        doubleStack1.getSecondStack().push(3);
        assertEquals(4, doubleStack1.getFirstStack().size());
        assertEquals(4, doubleStack1.getSecondStack().size());
        doubleStack1.getFirstStack().clear();
        doubleStack1.getSecondStack().clear();
        assertEquals(true, doubleStack1.getFirstStack().isEmpty());
        assertEquals(true, doubleStack1.getSecondStack().isEmpty());
    }

    /**
     * Test which clears the stack and then adds elements after.
     * @throws StackOverflowException if the stack has no more space left
     * @throws StackEmptyException if the stack is empty
     */
    @Test
    public void testClearThenPush() throws StackOverflowException, StackEmptyException {
        IDoubleStack doubleStack1 = getFactory().makeDoubleStack(DEFAULT_MAX_SIZE);
        doubleStack1.getFirstStack().push(0);
        doubleStack1.getFirstStack().push(1);
        doubleStack1.getFirstStack().push(2);
        doubleStack1.getFirstStack().push(3);
        doubleStack1.getSecondStack().push(0);
        doubleStack1.getSecondStack().push(1);
        doubleStack1.getSecondStack().push(2);
        doubleStack1.getSecondStack().push(3);
        assertEquals(4, doubleStack1.getFirstStack().size());
        assertEquals(4, doubleStack1.getSecondStack().size());
        doubleStack1.getFirstStack().clear();
        doubleStack1.getSecondStack().clear();
        assertEquals(true, doubleStack1.getFirstStack().isEmpty());
        assertEquals(true, doubleStack1.getSecondStack().isEmpty());
        doubleStack1.getFirstStack().push(2);
        doubleStack1.getFirstStack().push(3);
        doubleStack1.getSecondStack().push(0);
        doubleStack1.getSecondStack().push(1);
        assertEquals(2, doubleStack1.getFirstStack().size());
        assertEquals(3, doubleStack1.getFirstStack().pop());
        assertEquals(2, doubleStack1.getSecondStack().size());
        assertEquals(1, doubleStack1.getSecondStack().pop());
    }

    /**
     * Tests the top() method
     * @throws StackOverflowException if the stack has no more space left
     * @throws StackEmptyException if the stack is empty
     */
    @Test
    public void testTop() throws StackOverflowException, StackEmptyException {
        IDoubleStack doubleStack1 = getFactory().makeDoubleStack(DEFAULT_MAX_SIZE);
        doubleStack1.getFirstStack().push(0);
        doubleStack1.getFirstStack().push(1);
        doubleStack1.getFirstStack().push(2);
        doubleStack1.getFirstStack().push(3);
        doubleStack1.getSecondStack().push(0);
        doubleStack1.getSecondStack().push(1);
        doubleStack1.getSecondStack().push(2);
        doubleStack1.getSecondStack().push(3);
        assertEquals(4, doubleStack1.getFirstStack().size());
        assertEquals(4, doubleStack1.getSecondStack().size());
        assertEquals(3, doubleStack1.getFirstStack().top());
        assertEquals(3, doubleStack1.getSecondStack().top());
        doubleStack1.getFirstStack().pop();
        doubleStack1.getSecondStack().pop();
        assertEquals(2, doubleStack1.getFirstStack().top());
        assertEquals(2, doubleStack1.getSecondStack().top());
    }

    /**
     * Test which gets the top of an empty stack.
     * @throws StackEmptyException if the stack is empty
     */
    @Test (expected =  StackEmptyException.class)
    public void testEmptyTop() throws StackEmptyException {
        IDoubleStack doubleStack1 = getFactory().makeDoubleStack(DEFAULT_MAX_SIZE);
        assertEquals(true, doubleStack1.getFirstStack().isEmpty());
        doubleStack1.getFirstStack().top();
    }

    /**
     * Test which gets the top of a stack whose top element is null.
     * @throws StackOverflowException if the stack has no more space left
     * @throws StackEmptyException if the stack is empty
     */
    @Test
    public void testNullTop() throws StackOverflowException, StackEmptyException {
        IDoubleStack doubleStack1 = getFactory().makeDoubleStack(DEFAULT_MAX_SIZE);
        doubleStack1.getFirstStack().push(0);
        doubleStack1.getFirstStack().push(1);
        doubleStack1.getFirstStack().push(2);
        doubleStack1.getFirstStack().push(3);
        doubleStack1.getFirstStack().push(null);
        doubleStack1.getSecondStack().push(0);
        doubleStack1.getSecondStack().push(1);
        doubleStack1.getSecondStack().push(2);
        doubleStack1.getSecondStack().push(3);
        doubleStack1.getSecondStack().push(null);
        assertEquals(null, doubleStack1.getFirstStack().top());
        assertEquals(null, doubleStack1.getSecondStack().top());
        assertEquals(5, doubleStack1.getFirstStack().size());
        assertEquals(5, doubleStack1.getSecondStack().size());
    }

    /**
     * Test which sets the maximum size of the Double to 0 and pushes an element into a stack.
     * Test expected to run into exception since a size 0 DoubleStack cannot contain any elements.
     * @throws StackOverflowException if the stack has no more space left
     */
    @Test (expected = StackOverflowException.class)
    public void testDoubleStackOfSizeZeroFirstStack() throws StackOverflowException {
        IDoubleStack doubleStack1 = getFactory().makeDoubleStack(0);
        doubleStack1.getFirstStack().push(0);
    }

    /**
     * Test is the same above, only rather than using stack one, stack two is used to show consistency across
     * both stacks.
     * Test expected to run into exception since a size 0 DoubleSrack cannot contain any elements.
     * @throws StackOverflowException if the stack has no more space left
     */
    @Test (expected = StackOverflowException.class)
    public void testDoubleStackOfSizeZeroSecondStack() throws StackOverflowException {
        IDoubleStack doubleStack1 = getFactory().makeDoubleStack(0);
        doubleStack1.getSecondStack().push(0);
    }

    @Test (expected = StackOverflowException.class)
    public void testAddingOnlyNullToStackOne() throws StackOverflowException {
        IDoubleStack doubleStack1 = getFactory().makeDoubleStack(DEFAULT_MAX_SIZE);
        doubleStack1.getFirstStack().push(null);
        doubleStack1.getFirstStack().push(null);
        doubleStack1.getFirstStack().push(null);
        doubleStack1.getFirstStack().push(null);
        doubleStack1.getFirstStack().push(null);
        doubleStack1.getFirstStack().push(null);
        doubleStack1.getFirstStack().push(null);
        doubleStack1.getFirstStack().push(null);
        doubleStack1.getFirstStack().push(null);
        doubleStack1.getFirstStack().push(null);
        doubleStack1.getFirstStack().push(null);
    }

    @Test (expected = StackOverflowException.class)
    public void testAddingOnlyNullToStackTwo() throws StackOverflowException {
        IDoubleStack doubleStack1 = getFactory().makeDoubleStack(DEFAULT_MAX_SIZE);
        doubleStack1.getSecondStack().push(null);
        doubleStack1.getSecondStack().push(null);
        doubleStack1.getSecondStack().push(null);
        doubleStack1.getSecondStack().push(null);
        doubleStack1.getSecondStack().push(null);
        doubleStack1.getSecondStack().push(null);
        doubleStack1.getSecondStack().push(null);
        doubleStack1.getSecondStack().push(null);
        doubleStack1.getSecondStack().push(null);
        doubleStack1.getSecondStack().push(null);
        doubleStack1.getSecondStack().push(null);
    }

    @Test
    public void testClearStackOneStackAndAddToStackTwo() throws StackOverflowException {
        IDoubleStack doubleStack1 = getFactory().makeDoubleStack(DEFAULT_MAX_SIZE);
        doubleStack1.getFirstStack().push(null);
        doubleStack1.getFirstStack().push(null);
        doubleStack1.getFirstStack().push(null);
        doubleStack1.getFirstStack().push(null);
        doubleStack1.getFirstStack().push(null);
        doubleStack1.getFirstStack().push(null);
        doubleStack1.getFirstStack().push(null);
        doubleStack1.getFirstStack().push(null);
        doubleStack1.getFirstStack().push(null);
        doubleStack1.getFirstStack().push(null);
        doubleStack1.getFirstStack().clear();
        doubleStack1.getSecondStack().push(null);
        doubleStack1.getSecondStack().push(null);
        doubleStack1.getSecondStack().push(null);
        doubleStack1.getSecondStack().push(null);
        doubleStack1.getSecondStack().push(null);
        doubleStack1.getSecondStack().push(null);
        doubleStack1.getSecondStack().push(null);
        doubleStack1.getSecondStack().push(null);
        assertEquals(0, doubleStack1.getFirstStack().size());
        assertEquals(8, doubleStack1.getSecondStack().size());
    }

    @Test
    public void testClearStackTwoStackAndAddToStackOne() throws StackOverflowException {
        IDoubleStack doubleStack1 = getFactory().makeDoubleStack(DEFAULT_MAX_SIZE);
        doubleStack1.getSecondStack().push(null);
        doubleStack1.getSecondStack().push(null);
        doubleStack1.getSecondStack().push(null);
        doubleStack1.getSecondStack().push(null);
        doubleStack1.getSecondStack().push(null);
        doubleStack1.getSecondStack().push(null);
        doubleStack1.getSecondStack().push(null);
        doubleStack1.getSecondStack().push(null);
        doubleStack1.getSecondStack().push(null);
        doubleStack1.getSecondStack().push(null);
        doubleStack1.getSecondStack().clear();
        doubleStack1.getFirstStack().push(null);
        doubleStack1.getFirstStack().push(null);
        doubleStack1.getFirstStack().push(null);
        doubleStack1.getFirstStack().push(null);
        doubleStack1.getFirstStack().push(null);
        doubleStack1.getFirstStack().push(null);
        doubleStack1.getFirstStack().push(null);
        doubleStack1.getFirstStack().push(null);
        doubleStack1.getFirstStack().push(null);
        doubleStack1.getFirstStack().push(null);
        assertEquals(10, doubleStack1.getFirstStack().size());
        assertEquals(0, doubleStack1.getSecondStack().size());
    }


}
