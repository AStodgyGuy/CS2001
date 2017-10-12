package uk.ac.standrews.cs.cs2001.w03.impl;

import uk.ac.standrews.cs.cs2001.w03.common.InvalidProductPriceException;
import uk.ac.standrews.cs.cs2001.w03.interfaces.IVendingMachineProduct;

/**
 * This class represents products that can be stocked and sold in a vending machine in a specific lane.
 */
public class VendingMachineProduct implements IVendingMachineProduct {

    private String laneCode;
    private String description;
    private double price;

    /**
     * Constructor for VendingMachineProduct.
     *
     * @param laneCode    the lanecode of the product
     * @param description the description of the product
     */
    public VendingMachineProduct(String laneCode, String description, double price) throws InvalidProductPriceException {
        this.laneCode = laneCode;
        this.description = description;
        if (price > 0) {
            this.price = price;
        } else {
            throw new InvalidProductPriceException("Error: Product cannot have negative price!");
        }
    }

    /**
     * Getter method for lanecode.
     *
     * @return the product's lanecode
     */
    @Override
    public String getLaneCode() {
        return laneCode;
    }

    /**
     * Getter method for description.
     *
     * @return the product's description
     */
    @Override
    public String getDescription() {
        return description;
    }

    /**
     * @return price of the item
     */
    @Override
    public Double getPrice() {
        return price;
    }

}
