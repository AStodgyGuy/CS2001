package uk.ac.standrews.cs.cs2001.w03.common;

/**
 * This exception should be used to indicate that there cannot be a product with a negative price
 */
public class InvalidProductPriceException extends Exception {

    /**
     * Constructor for the exception.
     *
     * @param message to explain to the user that the error has occured
     */
    public InvalidProductPriceException(String message) {
        super(message);
    }
}
