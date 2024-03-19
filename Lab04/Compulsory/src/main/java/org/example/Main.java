package org.example;

import java.util.*;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {

        Problem p = new Problem();
        p.setRandomGroup();

        p.printDrivers();
        p.printPassengers();

        p.printDriversSortedByAge();
        p.printPassengersSortedByName();

    }
}

