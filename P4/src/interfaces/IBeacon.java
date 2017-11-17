package interfaces;

import impl.Beacon;

/**
 * Interface for a coordinate object
 */
public interface IBeacon {

    /**
     * Method which calculates the distance between the beacon and the target
     * @param target the target beacon
     */
    void calculateDistance(IBeacon target);

    /**
     * Method which returns the X-coord of the beacon
     * @return the x coordinate of the beacon
     */
    int getXcoord();

    /**
     * Method which returns the Y-coord of the beacon
     * @return the y coordinate of the beacon
     */
    int getYcoord();

    /**
     * Method which returns the distance between the beacon and the target
     * @return the distance
     */
    Double getDistance();

    /**
     * Method to print out the beacon
     */
    void printBeacon();

}