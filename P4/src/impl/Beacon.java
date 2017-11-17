package impl;

import interfaces.IBeacon;

/**
 * Class which represents a beacon in a grid
 */
public class Beacon implements IBeacon {

    private int xcoord;
    private int ycoord;
    private Double distanceToTarget;


    public Beacon(int x, int y) {
        this.xcoord = x;
        this.ycoord = y;
    }

    /**
     * Method which returns the X-coord of the beacon
     * @return the x coordinate of the beacon
     */
    @Override
    public int getXcoord() {
        return xcoord;
    }

    /**
     * Method which returns the Y-coord of the beacon
     * @return the y coordinate of the beacon
     */
    @Override
    public int getYcoord() {
        return ycoord;
    }

    /**
     * Method which returns the distance between the beacon and the target
     * @return the distance
     */
    @Override
    public Double getDistance() {
        return distanceToTarget;
    }

    /**
     * Method which calculates the distance between the beacon and the target
     * @param target the target beacon
     */
    @Override
    public void calculateDistance(IBeacon target) {
        int distanceX = target.getXcoord() - xcoord;
        int distanceY = target.getYcoord() - ycoord;
        this.distanceToTarget = Math.sqrt( Math.pow(distanceX, 2.0) + Math.pow(distanceY, 2.0));
    }

    /**
     * Method which prints out the beacon
     */
    @Override
    public void printBeacon() {
        System.out.println("Beacon point at " + this.xcoord + ", " + this.ycoord);
    }
}