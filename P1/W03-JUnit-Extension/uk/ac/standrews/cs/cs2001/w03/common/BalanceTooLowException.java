package uk.ac.standrews.cs.cs2001.w03.common;

/**
 * This exception should be used to indicate the customer's balance is too low to make the purchase
 */
public class BalanceTooLowException extends Exception {

    /**
     * Constructor for the exception.
     *
     * @param message to explain to the user that the error has occured
     */
    public BalanceTooLowException(String message) {
        super(message);
    }

}
