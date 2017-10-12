package uk.ac.standrews.cs.cs2001.w03.impl;

import uk.ac.standrews.cs.cs2001.w03.common.*;
import uk.ac.standrews.cs.cs2001.w03.interfaces.ICustomer;
import uk.ac.standrews.cs.cs2001.w03.interfaces.IProductRecord;
import uk.ac.standrews.cs.cs2001.w03.interfaces.IVendingMachine;
import uk.ac.standrews.cs.cs2001.w03.interfaces.IVendingMachineProduct;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * This class represents a simple vending machine which can stock and sell products.
 */
public class VendingMachine extends AbstractFactoryClient implements IVendingMachine {

    private HashMap<String, IProductRecord> vendingMachineProductList = new HashMap<>();
    private ArrayList<ICustomer> customerList = new ArrayList<>();

    /**
     * Empty constructor to create new vending machine.
     */
    public VendingMachine() {
    }

    /**
     * Registers the specified product for sale in the vending machine.
     *
     * @param vendingMachineProduct the item to register
     * @throws LaneCodeAlreadyInUseException if duplicate lanecode exists
     */
    @Override
    public void registerProduct(IVendingMachineProduct vendingMachineProduct) throws LaneCodeAlreadyInUseException {

        try {
            IProductRecord pr = vendingMachineProductList.get(vendingMachineProduct.getLaneCode());
            //store the product
            if (pr == null) {
                if (vendingMachineProduct.getLaneCode() != null && vendingMachineProduct.getPrice() != null) {
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
            throw new NullPointerException("Error: Null object cannot be unregistered with the vending machine");
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
     * Method which adds item to the customer basket.
     *
     * @param vendingMachineProduct the item to be added to the basket
     * @param customer              the customer whose adding the item to basket
     * @throws LaneCodeNotRegisteredException if the lane code has not been registered for use in the vending machine
     * @throws ProductUnavailableException    if there is not enough stock to purchase the item
     * @throws CustomerDoesntExistException   if the customer is not registered with the vending machine
     */
    @Override
    public void addItemToBasket(IVendingMachineProduct vendingMachineProduct, ICustomer customer) throws LaneCodeNotRegisteredException, ProductUnavailableException, CustomerDoesntExistException {
        if (vendingMachineProduct != null) {
            if (vendingMachineProductList.containsKey(vendingMachineProduct.getLaneCode())) {
                if (customerList.contains(customer)) {
                    vendingMachineProductList.get(vendingMachineProduct.getLaneCode()).buyItem();
                    customer.addProductToBasket(vendingMachineProduct);
                } else {
                    throw new CustomerDoesntExistException("Error: The Customer is not registered!");
                }
            } else {
                throw new LaneCodeNotRegisteredException("Error: lanecode is not registered!");
            }
        } else {
            throw new LaneCodeNotRegisteredException("Error: lanecode is cannot be null!");
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
     * Method which buys the basket of the customer.
     *
     * @param customer the customer buying the basket
     * @throws LaneCodeNotRegisteredException if the lane code has not been registered for use in the vending machine
     * @throws CustomerDoesntExistException   if the customer is not registered with the vending machine
     * @throws BalanceTooLowException         if the customer does not have enough money to purchase the basket
     */
    @Override
    public void buyBasket(ICustomer customer) throws LaneCodeNotRegisteredException, CustomerDoesntExistException, BalanceTooLowException, AddingInvalidFundsException {
        int index = customerList.indexOf(customer);
        if (index >= 0) {
            customerList.get(index).reduceBalance(customer.getTotalBasketCost());
            customerList.get(index).getCustomerBasket().clear();
        } else {
            throw new CustomerDoesntExistException("Error: The customer is not recognised, please register the customer");
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
     * @return the most popular product
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
        if (mostPopular == null) {
            throw new LaneCodeNotRegisteredException("Error: lanecode is not registered!");
        }

        return mostPopular;
    }

    /**
     * Method which adds a new customer to the customer arraylist.
     *
     * @param customer the new customer to be added
     */
    @Override
    public void registerNewCustomer(ICustomer customer) {
        if (customer != null) {
            customerList.add(customer);
        } else {
            throw new NullPointerException("Error: Customer cannot be null!");
        }
    }

    /**
     * Method which removes a customer from the customer arraylist.
     *
     * @param customer the customer to be unregistered
     */
    @Override
    public void unregisterCustomer(ICustomer customer) throws CustomerDoesntExistException {
        if (customerList.contains(customer)) {
            customerList.remove(customer);
        } else {
            throw new CustomerDoesntExistException("Error: Customer is not registered!");
        }
    }

    /**
     * Method which returns the number of customers.
     *
     * @return the number of customers on the list
     */
    @Override
    public int getNumberOfCustomers() {
        return customerList.size();
    }

    /**
     * Method which returns the list of customers
     *
     * @return the customer list
     */
    @Override
    public ArrayList<ICustomer> getCustomerList() { return customerList; }

    /**
     * Method which returns the list of products registered in the vending machine
     *
     * @return the vending machine product list
     */
    @Override
    public HashMap<String, IProductRecord> getProductList() { return vendingMachineProductList; }

}
