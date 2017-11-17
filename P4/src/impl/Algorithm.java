package impl;

import interfaces.IAlgorithm;
import interfaces.IBeacon;

import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.List;

public class Algorithm implements IAlgorithm {

    public Algorithm() {}

    /**
     * Method for algorithm 1 which sorts at insertion
     *
     * @param x  the x-coord of the target
     * @param y  the y-coord of the target
     * @param k  the number of results to return
     * @param bs the list of coordinates
     * @return A map of k elements that are closest to the target
     */
    @Override
    public IBeacon[] algorithmOne(int x, int y, int k, List<IBeacon> bs) {
        BeaconComparator bc = new BeaconComparator();
        PriorityQueue<IBeacon> bpq = new PriorityQueue<>(bs.size(), bc);
        Beacon target = new Beacon(x,y);
        for (IBeacon i: bs) {
            i.calculateDistance(target);
            //sort at insertion
            bpq.add(i);
        }
        IBeacon[] result = new IBeacon[k];
        for (int j = 0; j < k; j++) {
            result[j] = bpq.remove();
        }
        return result;
    }

    /**
     * Method for algorithm 2 which inserts all and then sorts
     *
     * @param x  the x-coord of the target
     * @param y  the y-coord of the target
     * @param k  the number of results to return
     * @param bs the list of coordinates
     * @return A map of k elements that are closest to the target
     */
    @Override
    public IBeacon[] algorithmTwo(int x, int y, int k, List<IBeacon> bs) {
        Beacon target = new Beacon(x,y);
        BeaconComparator bc = new BeaconComparator();
        List<IBeacon> al = new ArrayList<>();
        for (IBeacon i: bs) {
            i.calculateDistance(target);
            al.add(i);
        }
        //sort once
        al.sort(bc);

        IBeacon[] result = new Beacon[k];
        for (int j = 0; j < k; j++) {
            result[j] = al.get(j);
        }
        return result;
    }

    /**
     * Method for algorithm 3 which does not sort at all
     *
     * @param x  the x-coord of the target
     * @param y  the y-coord of the target
     * @param k  the number of results to return
     * @param bs the list of coordinates
     * @return A map of k elements that are closest to the target
     */
    @Override
    public IBeacon[] algorithmThree(int x, int y, int k, List<IBeacon> bs) {
        Beacon target = new Beacon(x,y);
        List<IBeacon> al = new ArrayList<>();
        for (IBeacon i: bs) {
            i.calculateDistance(target);
            if (al.size() < k) {
                al.add(i);
            } else {
                Double dmax = -1.0;
                IBeacon beaconToRemove = null;
                //finds maximum distance in al
                for (IBeacon j: al) {
                    if (j.getDistance() > dmax) {
                        dmax = j.getDistance();
                        beaconToRemove = j;
                    }
                }
                if (i.getDistance() < dmax) {
                    al.remove(beaconToRemove);
                    al.add(i);
                }
            }
        }
        return al.toArray(new IBeacon[al.size()]);
    }
}