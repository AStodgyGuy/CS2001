package impl;

import interfaces.ICoordinate;

public class Coordinate implements ICoordinate, Comparable {

    private int xcoord;
    private int ycoord;


    public Coordinate(int x, int y) {
        this.xcoord = x;
        this.ycoord = y;
    }

    public int getXcoord() {
        return xcoord;
    }

    public int getYcoord() {
        return ycoord;
    }

} 