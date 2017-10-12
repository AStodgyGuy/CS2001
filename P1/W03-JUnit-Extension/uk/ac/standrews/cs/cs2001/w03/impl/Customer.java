package uk.ac.standrews.cs.cs2001.w03.impl;

import uk.ac.standrews.cs.cs2001.w03.common.AddingInvalidFundsException;
import uk.ac.standrews.cs.cs2001.w03.common.BalanceTooLowException;
import uk.ac.standrews.cs.cs2001.w03.interfaces.ICustomer;
import uk.ac.standrews.cs.cs2001.w03.interfaces.IVendingMachineProduct;

import java.util.ArrayList;

/**
 * This class represents a Customer, recording information relating to a customer who buys things from the vending machine.
 */
public class Customer implements ICustomer {

    private String name;
    private double balance;
    private ArrayList<IVendingMachineProduct> basket = new ArrayList<>();

    /**
     * Constructor for Customer.
     */
    public Customer(String name, double balance) throws AddingInvalidFundsException {
        this.name = name;
        if (balance >= 0) {
            this.balance = balance;
        } else {
            throw new AddingInvalidFundsException("Error: Cannot create a customer with negative balance!");
        }
    }

    /**
     * Method to retrieve the customers name.
     *
     * @return the name of the customer
     */
    @Override
    public String getCustomerName() {
        return name;
    }

    /**
     * Method to retrieve the customers balance.
     *
     * @return the customer's account balance
     */
    @Override
    public double getCustomerBalance() {
        return balance;
    }

    /**
     * Method which adds money to the customer's balance.
     *
     * @param moneyToBeAdded the amount to be added to the customers balance
     */
    @Override
    public void addMoney(Double moneyToBeAdded) throws AddingInvalidFundsException {
        if (moneyToBeAdded != null) {
            if (moneyToBeAdded >= 0) {
                this.balance = this.balance + moneyToBeAdded;
            } else {
                throw new AddingInvalidFundsException("Error: Cannot add zero or negative balance to account!");
            }
        } else {
            throw new AddingInvalidFundsException("Error: Cannot add null balance to account!");
        }
    }

    /**
     * Method which reduces the cost of the item from the balance.
     *
     * @param moneyToBeReduced the cost of the item
     * @throws BalanceTooLowException if the customer's balance is too low to make the purchase
     */
    @Override
    public void reduceBalance(Double moneyToBeReduced) throws BalanceTooLowException, AddingInvalidFundsException {
        if (moneyToBeReduced != null) {
            if (moneyToBeReduced < 0) {
                throw new AddingInvalidFundsException("Error: Cannot reduce balance by negative amount");
            } else if (this.balance - moneyToBeReduced < 0) {
                throw new BalanceTooLowException("Error: Cannot make this purchase as balance is too low, please add money to your balance");
            } else {
                this.balance = this.balance - moneyToBeReduced;
            }
        } else {
            throw new AddingInvalidFundsException("Error: Cannot reduce balance by null amount");
        }

    }

    /**
     * Method which adds products to the customer's basket.
     *
     * @param vendingMachineProduct the product to be added to the basket
     */
    @Override
    public void addProductToBasket(IVendingMachineProduct vendingMachineProduct) {
        basket.add(vendingMachineProduct);
    }

    /**
     * Method which returns the customer's basket.
     *
     * @return the customer's basket
     */
    @Override
    public ArrayList<IVendingMachineProduct> getCustomerBasket() {
        return basket;
    }

    /**
     * Method which returns the customer's basket cost.
     *
     * @return the customer's basket cost
     */
    @Override
    public double getTotalBasketCost() {
        double price = 0.0;
        for (IVendingMachineProduct product : basket) {
            price = price + product.getPrice();
        }
        return price;
    }
}
