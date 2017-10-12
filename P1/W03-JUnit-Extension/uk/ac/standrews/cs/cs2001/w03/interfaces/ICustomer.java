package uk.ac.standrews.cs.cs2001.w03.interfaces;

import uk.ac.standrews.cs.cs2001.w03.common.AddingInvalidFundsException;
import uk.ac.standrews.cs.cs2001.w03.common.BalanceTooLowException;

import java.util.ArrayList;


/**
 * Interface for a customer buying an item from the vending machine
 */
public interface ICustomer {

    /**
     * Returns the name of the customer.
     *
     * @returns name of the customer
     */
    String getCustomerName();

    /**
     * Returns the balance of the customer.
     *
     * @returns the account balance
     */
    double getCustomerBalance();

    /**
     * Method which adds money to the balance.
     *
     * @param moneyToBeAdded
     */
    void addMoney(Double moneyToBeAdded) throws AddingInvalidFundsException;

    /**
     * Method which reduces the money in the balance.
     *
     * @param moneyToBeReduced
     */
    void reduceBalance(Double moneyToBeReduced) throws BalanceTooLowException, AddingInvalidFundsException;

    /**
     * Method which adds a vending machine product to the customer's basket.
     */
    void addProductToBasket(IVendingMachineProduct vendingMachineProduct);

    /**
     * Method which returns the customer basket.
     *
     * @returns the items in the customer's basket
     */
    ArrayList<IVendingMachineProduct> getCustomerBasket();

    /**
     * Method which returns the cost of the customer's basket
     *
     * @return the cost of the basket
     */
    double getTotalBasketCost();
}
