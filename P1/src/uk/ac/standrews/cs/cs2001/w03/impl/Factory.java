package uk.ac.standrews.cs.cs2001.w03.impl;

import uk.ac.standrews.cs.cs2001.w03.interfaces.IFactory;
import uk.ac.standrews.cs.cs2001.w03.interfaces.IProductRecord;
import uk.ac.standrews.cs.cs2001.w03.interfaces.IVendingMachine;
import uk.ac.standrews.cs.cs2001.w03.interfaces.IVendingMachineProduct;


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
     * @return
     */
    @Override
    public IVendingMachineProduct makeVendingMachineProduct(String laneCode, String description) {
        return new VendingMachineProduct(laneCode, description);
    }

    /**
     * Creates a new ProductRecord.
     *
     * @param vendingMachineProduct the product to use for this record
     * @return
     */
    @Override
    public IProductRecord makeProductRecord(IVendingMachineProduct vendingMachineProduct) {
        return new ProductRecord(vendingMachineProduct);
    }

    /**
     * Creates an instance of vending machine.
     *
     * @return instance of vending machine
     */
    @Override
    public IVendingMachine makeVendingMachine() {
        return new VendingMachine();
    }

}
