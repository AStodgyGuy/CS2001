package uk.ac.standrews.cs.cs2001.w03.impl;

import uk.ac.standrews.cs.cs2001.w03.common.AbstractFactoryClient;
import uk.ac.standrews.cs.cs2001.w03.common.LaneCodeAlreadyInUseException;
import uk.ac.standrews.cs.cs2001.w03.common.LaneCodeNotRegisteredException;
import uk.ac.standrews.cs.cs2001.w03.common.ProductUnavailableException;
import uk.ac.standrews.cs.cs2001.w03.interfaces.IProductRecord;
import uk.ac.standrews.cs.cs2001.w03.interfaces.IVendingMachine;
import uk.ac.standrews.cs.cs2001.w03.interfaces.IVendingMachineProduct;

import java.util.HashMap;
import java.util.Map;

/**
 * This class represents a simple vending machine which can stock and sell products.
 */
public class VendingMachine extends AbstractFactoryClient implements IVendingMachine {

    private HashMap<String, IProductRecord> vendingMachineProductList = new HashMap<>();

    /**
     * Empty constructor to create new vending machine.
     */
    public VendingMachine() {
    }

    /**
     * Registers the specified product for sale in the vending machine.
     *
     * @param vendingMachineProduct the item to register
     * @throws LaneCodeAlreadyInUseException if duplicate laneCode exists
     */
    @Override
    public void registerProduct(IVendingMachineProduct vendingMachineProduct) throws LaneCodeAlreadyInUseException {

        try {
            IProductRecord pr = vendingMachineProductList.get(vendingMachineProduct.getLaneCode());
            //store the product
            if (pr == null) {
                if (vendingMachineProduct.getLaneCode() != null) {
                    pr = getFactory().makeProductRecord(vendingMachineProduct);
                    vendingMachineProductList.put(vendingMachineProduct.getLaneCode(), pr);
                } else {
                    throw new NullPointerException("Error: Product cannot have null lane code");
                }
            } else {
                throw new LaneCodeAlreadyInUseException("Error: lanecode is already in use! Duplicate has been rejected from the vending machine");
            }
        } catch (NullPointerException e) {
            throw new NullPointerException("Error: Null object cannot be registered with the vending machine");
        }

    }

    /**
     * Removes the product from the hashmap.
     *
     * @param vendingMachineProduct the item to remove
     * @throws LaneCodeNotRegisteredException error thrown if the item does not exist in the hashmap
     */
    @Override
    public void unregisterProduct(IVendingMachineProduct vendingMachineProduct) throws LaneCodeNotRegisteredException {
        try {
            //find product in hashmap and remove it
            IProductRecord pr = vendingMachineProductList.get(vendingMachineProduct.getLaneCode());
            if (pr != null) {
                vendingMachineProductList.remove(vendingMachineProduct.getLaneCode());
            } else {
                throw new LaneCodeNotRegisteredException("Error: lanecode is not registered!");
            }
        } catch (NullPointerException e) {
            throw new LaneCodeNotRegisteredException("Error: Null laneCode cannot be unregistered with the vending machine");
        }


    }

    /**
     * Returns the size of the hashmap vendingMachineProductList.
     *
     * @return number of products in vendingMachineProductList
     */
    @Override
    public int getNumberOfProducts() {
        return vendingMachineProductList.size();
    }

    /**
     * Adds one item of stock to a vending machine lane.
     *
     * @param laneCode the lane code of the product, e.g. A1, A2, A3, B1, ... in the vending machine
     * @throws LaneCodeNotRegisteredException if the lane code has not been registered for use in the vending machine
     */
    @Override
    public void addItem(String laneCode) throws LaneCodeNotRegisteredException {
        //add the product
        if (vendingMachineProductList.containsKey(laneCode)) {
            vendingMachineProductList.get(laneCode).addItem();
        } else {
            throw new LaneCodeNotRegisteredException("Error: lanecode is not registered!");
        }
    }

    /**
     * Method which loops through vendingMachineProductList and counts the number of items.
     *
     * @return the total number of items in the hashmap for stock level
     */
    @Override
    public int getTotalNumberOfItems() {
        int totalNumber = 0;
        for (Map.Entry<String, IProductRecord> laneCode : vendingMachineProductList.entrySet()) {
            totalNumber = totalNumber + laneCode.getValue().getNumberAvailable();
        }
        return totalNumber;
    }

    /**
     * Method which returns the number of items of a particular lanecode.
     *
     * @param laneCode the lane code in the machine
     * @return stock level for the lanecode item
     * @throws LaneCodeNotRegisteredException if the lane code has not been registered for use in the vending machine
     */
    @Override
    public int getNumberOfItems(String laneCode) throws LaneCodeNotRegisteredException {
        try {
            int value = vendingMachineProductList.get(laneCode).getNumberAvailable();
            if (value >= 0) {
                return value;
            } else {
                throw new LaneCodeNotRegisteredException("Error: lanecode is not registered!");
            }
        } catch (NullPointerException e) {
            throw new LaneCodeNotRegisteredException("Error: lanecode is not registered!");
        }
    }

    /**
     * Method which buys an item from the vendingMachineProductList.
     *
     * @param laneCode the lane code of the item, e.g. A1, A2, A3, B1, ... in the vending machine
     * @throws ProductUnavailableException    if the specified lane is empty
     * @throws LaneCodeNotRegisteredException if the given lane code has not been registered for use in the vending machine
     */
    @Override
    public void buyItem(String laneCode) throws LaneCodeNotRegisteredException, ProductUnavailableException {
        if (vendingMachineProductList.containsKey(laneCode)) {
            vendingMachineProductList.get(laneCode).buyItem();
        } else {
            throw new LaneCodeNotRegisteredException("Error: lanecode is not registered!");
        }
    }

    /**
     * Method which returns how many times a product has been sold.
     *
     * @param laneCode the lane code of the product
     * @return number of times a specific product has been sold
     * @throws LaneCodeNotRegisteredException if the given lane code has not been registered for use in the vending machine
     */
    @Override
    public int getNumberOfSales(String laneCode) throws LaneCodeNotRegisteredException {
        if (vendingMachineProductList.containsKey(laneCode)) {
            return vendingMachineProductList.get(laneCode).getNumberOfSales();
        } else {
            throw new LaneCodeNotRegisteredException("Error: lanecode is not registered!");
        }
    }

    /**
     * Method which determines which product is the most popular.
     *
     * @return the most popular product or null if no items have been bought
     * @throws LaneCodeNotRegisteredException if the given lane code has not been registered for use in the vending machine
     */
    @Override
    public IVendingMachineProduct getMostPopular() throws LaneCodeNotRegisteredException {
        IVendingMachineProduct mostPopular = null;
        int highestNumber = 0;

        //go through each product in the hashmap
        for (Map.Entry<String, IProductRecord> laneCode : vendingMachineProductList.entrySet()) {

            //only retrieve products which have been purchased a greater number of times
            if (laneCode.getValue().getNumberOfSales() > highestNumber) {
                mostPopular = laneCode.getValue().getProduct();
                highestNumber = laneCode.getValue().getNumberOfSales();
            }
        }

        //if nothing has been bought throw an exception
        if (mostPopular == null && vendingMachineProductList.size() == 0) {
            throw new LaneCodeNotRegisteredException("Error: lanecode is not registered!");
        }

        return mostPopular;
    }

}
