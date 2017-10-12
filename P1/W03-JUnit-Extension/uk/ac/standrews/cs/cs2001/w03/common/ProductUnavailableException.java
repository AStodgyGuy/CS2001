package uk.ac.standrews.cs.cs2001.w03.common;

/**
 * This exception should be used to indicate that a product is not available.
 */
public class ProductUnavailableException extends Exception {

    /**
     * Constructor for the exception.
     *
     * @param message to explain to the user that the error has occured
     */
    public ProductUnavailableException(String message) {
        super(message);
    }
}
