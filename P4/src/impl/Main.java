package impl;

import interfaces.IBeacon;

import java.util.concurrent.TimeUnit;
import java.lang.System;
import java.util.concurrent.ThreadLocalRandom;
import java.util.List;
import java.util.ArrayList;

public class Main {

    private static Algorithm algo = new Algorithm();


    public static void main(String[] args) {
        ArrayList<IBeacon> al = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            int x = ThreadLocalRandom.current().nextInt(0,30);
            int y = ThreadLocalRandom.current().nextInt(0,30);
            al.add(new Beacon(x,y));
        }

        testAlgorithmTwo(al);
        testAlgorithmOne(al);
        testAlgorithmOne(al);
        testAlgorithmTwo(al);
        testAlgorithmOne(al);

    }


    //Test 1: Comparing time between the three algorithms for fixed n and k average of 10 runs

    public static void testAlgorithmOne(ArrayList<IBeacon> al) {
        long startTime = System.nanoTime();
        algo.algorithmOne(0,0,5, al);
        long endTime = System.nanoTime();

        System.out.println(endTime - startTime);
    }

    public static void testAlgorithmTwo(ArrayList<IBeacon> al) {
        long startTime = System.nanoTime();
        algo.algorithmTwo(0,0,5, al);
        long endTime = System.nanoTime();

        System.out.println(endTime - startTime);
    }

    public static void testAlgorithmThree() {

    }

    //Test 2: Keeping n constant but varying k

    //Test 3: Keeping k constant but varying n

    //Test 4: Test 1 for large values of n

    //Test 5: Test 1 for large values of k

    //Test 6: Test 1 for large n and large k

    //Test 7: Target beacon is very very far away

    //graph everything using excel
}