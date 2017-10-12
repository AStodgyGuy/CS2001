package uk.ac.standrews.cs.cs2001.w03.impl;

import uk.ac.standrews.cs.cs2001.w03.common.ProductUnavailableException;
import uk.ac.standrews.cs.cs2001.w03.interfaces.IProductRecord;
import uk.ac.standrews.cs.cs2001.w03.interfaces.IVendingMachineProduct;

/**
 * This class represents a ProductRecord, recording information relating to a product sold in a vending machine.
 */
public class ProductRecord implements IProductRecord {

    private IVendingMachineProduct vendingMachineProduct;
    private int numberAvailable;
    private int numberOfSales;

    /**
     * Constructor for intialising the Product with a record.
     *
     * @param vendingMachineProduct the product which contains the record
     */
    public ProductRecord(IVendingMachineProduct vendingMachineProduct) {
        this.vendingMachineProduct = vendingMachineProduct;
    }

    /**
     * Getter method for the product.
     *
     * @return the product
     */
    @Override
    public IVendingMachineProduct getProduct() {
        return vendingMachineProduct;
    }

    /**
     * Getter method for the number of times a product has been sold.
     *
     * @return the number of times the product has been sold
     */
    @Override
    public int getNumberOfSales() {
        return numberOfSales;
    }

    /**
     * Getter method for the number of product available to purchase.
     *
     * @return the number available to purchase
     */
    @Override
    public int getNumberAvailable() {
        return numberAvailable;
    }

    /**
     * Increments the number available.
     */
    @Override
    public void addItem() {
        this.numberAvailable++;
    }

    /**
     * Increments the number of sales whilst reducing the number available.
     *
     * @throws ProductUnavailableException occurs when there is not enough stock for the customer to buy
     */
    @Override
    public void buyItem() throws ProductUnavailableException {
        if (this.numberAvailable == 0) {
            throw new ProductUnavailableException("Error: There isn't enough of this item in stock!");
        } else {
            this.numberOfSales++;
            this.numberAvailable--;
        }
    }
}
