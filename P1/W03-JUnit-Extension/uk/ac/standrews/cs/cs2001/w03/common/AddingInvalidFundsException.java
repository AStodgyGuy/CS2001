package uk.ac.standrews.cs.cs2001.w03.common;

/**
 * This exception should be used to indicate to the customer that they cannot add a negative balance
 */
public class AddingInvalidFundsException extends Exception {

    /**
     * Constructor for the exception.
     *
     * @param message to explain to the user that the error has occured
     */
    public AddingInvalidFundsException(String message) {
        super(message);
    }
}
