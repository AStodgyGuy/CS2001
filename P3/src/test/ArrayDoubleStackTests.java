package test;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import common.AbstractFactoryClient;
import interfaces.IDoubleStack;

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

}
