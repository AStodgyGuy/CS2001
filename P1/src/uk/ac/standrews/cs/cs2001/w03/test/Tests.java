package uk.ac.standrews.cs.cs2001.w03.test;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import uk.ac.standrews.cs.cs2001.w03.common.AbstractFactoryClient;
import uk.ac.standrews.cs.cs2001.w03.interfaces.IVendingMachineProduct;
import uk.ac.standrews.cs.cs2001.w03.interfaces.IVendingMachine;

/**
 * This is a JUnit test class for the Vending Machine ADT.
 *
 */
public class Tests extends AbstractFactoryClient {


    /**
     * This is a silly test.
     */
    @Test
    public void sillyTest() {

        // Instantiate objects being tested without knowing which concrete classes are involved.
        // In this case, the test asserts that a vending machine and product are equal which makes no sense at all and should fail.
        // Your job is to write sensible tests that will test your implementation.
        IVendingMachine vendingMachine = getFactory().makeVendingMachine();
        IVendingMachineProduct vendingMachineProduct = getFactory().makeVendingMachineProduct("A1", "Haggis Crisps");

        assertEquals(vendingMachine, vendingMachineProduct);
    }

}
