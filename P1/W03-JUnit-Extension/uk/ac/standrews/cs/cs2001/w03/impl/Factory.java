package uk.ac.standrews.cs.cs2001.w03.impl;

import uk.ac.standrews.cs.cs2001.w03.common.AddingInvalidFundsException;
import uk.ac.standrews.cs.cs2001.w03.common.InvalidProductPriceException;
import uk.ac.standrews.cs.cs2001.w03.interfaces.*;


/**
 * This class implements a singleton factory.
 */
public final class Factory implements IFactory {

    private static IFactory factoryInstance = null;

    private Factory() {

    }

    /**
     * Method which returns an instance of the singleton Factory class.
     *
     * @return the instance of the Factory
     */
    public static IFactory getInstance() {
        if (factoryInstance == null) {
            factoryInstance = new Factory();
        }
        return factoryInstance;
    }

    /**
     * Creates a new VendingMachineProduct.
     *
     * @param laneCode    the item's lane code, i.e. which lane the item is in, e.g. A1, A2, A3, B1, ... in the vending machine
     * @param description the description of the item
     * @return instance of VendMachineProduct
     * @throws InvalidProductPriceException if the product is created with a negative price
     */
    @Override
    public IVendingMachineProduct makeVendingMachineProduct(String laneCode, String description, Double price) throws InvalidProductPriceException {
        return new VendingMachineProduct(laneCode, description, price);
    }

    /**
     * Creates a new ProductRecord.
     *
     * @param vendingMachineProduct the product to use for this record
     * @return instance of ProductRecord
     */
    @Override
    public IProductRecord makeProductRecord(IVendingMachineProduct vendingMachineProduct) {
        return new ProductRecord(vendingMachineProduct);
    }

    /**
     * Creates an instance of vending machine.
     *
     * @return instance of VendingMachine
     */
    @Override
    public IVendingMachine makeVendingMachine() {
        return new VendingMachine();
    }

    /**
     * Creates an instance of Customer.
     *
     * @param name    the name of the customer
     * @param balance the customer's balance
     * @return instance of Customer
     * @throws AddingInvalidFundsException if the balance at object creation is negative
     */
    @Override
    public ICustomer makeCustomer(String name, double balance) throws AddingInvalidFundsException {
        return new Customer(name, balance);
    }
}
