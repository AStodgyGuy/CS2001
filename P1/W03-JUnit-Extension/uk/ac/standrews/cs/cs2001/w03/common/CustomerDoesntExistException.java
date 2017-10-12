package uk.ac.standrews.cs.cs2001.w03.common;

/**
 * This exception should be used to indicate whether the customer does not exist
 */

public class CustomerDoesntExistException extends Exception {

    public CustomerDoesntExistException(String message) {
        super(message);
    }
}
