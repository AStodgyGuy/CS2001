package uk.ac.standrews.cs.cs2001.w03.interfaces;


import uk.ac.standrews.cs.cs2001.w03.common.AddingInvalidFundsException;
import uk.ac.standrews.cs.cs2001.w03.common.InvalidProductPriceException;

/**
 * Interface for a factory allowing the other interfaces to be instantiated without knowing the implementation classes.
 */
public interface IFactory {

    /**
     * Creates an instance of {@link IVendingMachineProduct}.
     *
     * @param laneCode    the item's lane code, i.e. which lane the item is in, e.g. A1, A2, A3, B1, ... in the vending machine
     * @param description the description of the item
     * @return the item
     */
    IVendingMachineProduct makeVendingMachineProduct(String laneCode, String description, Double price) throws InvalidProductPriceException;


    /**
     * This method creates an instance of {@link IProductRecord} for a new product.
     *
     * @param vendingMachineProduct the product to use for this record
     * @return the product record
     */
    IProductRecord makeProductRecord(IVendingMachineProduct vendingMachineProduct);


    /**
     * Creates an instance of {@link IVendingMachine}.
     *
     * @return the vending machine
     */
    IVendingMachine makeVendingMachine();

    /**
     * Creates an instance of (@Link ICustomer).
     *
     * @return the customer
     */
    ICustomer makeCustomer(String name, double balance) throws AddingInvalidFundsException;


}
