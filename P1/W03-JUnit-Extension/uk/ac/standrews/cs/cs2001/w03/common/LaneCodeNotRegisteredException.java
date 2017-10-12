package uk.ac.standrews.cs.cs2001.w03.common;

/**
 * This exception should be used to indicate that a lane code has been presented to the system before it has been registered for use for an item.
 */
public class LaneCodeNotRegisteredException extends Exception {

    /**
     * Constructor for the exception.
     *
     * @param message to explain to the user that the error has occured
     */
    public LaneCodeNotRegisteredException(String message) {
        super(message);
    }
}
