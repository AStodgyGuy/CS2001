package uk.ac.standrews.cs.cs2001.w03.common;

/**
 * This exception should be used to indicate the illegal state when multiple products share the same lane in the vending machine.
 */
public class LaneCodeAlreadyInUseException extends Exception {

    /**
     * Constructor for the exception.
     *
     * @param message to explain to the user that the error has occured
     */
    public LaneCodeAlreadyInUseException(String message) {
        super(message);
    }

}
