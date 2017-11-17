package impl;

import interfaces.IBeacon;
import java.util.Comparator;

public class BeaconComparator implements Comparator<IBeacon> {

    /**
     * Method which compares the distance to target between two beacons
     * @param o1 the beacon to compare
     * @param o2 the beacon to compare
     * @return an integer representing which is closer, if positive, beacon is closer to target than beacon o
     */
    @Override
    public int compare(IBeacon o1, IBeacon o2) {
        Double distance1 = o1.getDistance();
        Double distance2 = o2.getDistance();

        return distance1.compareTo(distance2);
    }
}
