package interfaces;

import java.util.List;

/**
 * Interface for an Algorithm class which contains the algorithm specified at:
 * https://studres.cs.st-andrews.ac.uk/CS2001/Practicals/W10-complexity/practical-wk10-complexity.pdf
 */
public interface IAlgorithm {

    /**
     * Method for algorithm 1 which sorts at insertion
     * @param x the x-coord of the target
     * @param y the y-coord of the target
     * @param k the number of results to return
     * @param bs the list of coordinates
     * @return A map of k elements that are closest to the target
     */
    IBeacon[] algorithmOne(int x, int y, int k, List<IBeacon> bs);

    /**
     * Method for algorithm 2 which inserts all and then sorts
     * @param x the x-coord of the target
     * @param y the y-coord of the target
     * @param k the number of results to return
     * @param bs the list of coordinates
     * @return A map of k elements that are closest to the target
     */
    IBeacon[] algorithmTwo(int x, int y, int k, List<IBeacon> bs);

    /**
     * Method for algorithm 3 which does not sort at all
     * @param x the x-coord of the target
     * @param y the y-coord of the target
     * @param k the number of results to return
     * @param bs the list of coordinates
     * @return A map of k elements that are closest to the target
     */
    IBeacon[] algorithmThree(int x, int y, int k, List<IBeacon> bs);

}