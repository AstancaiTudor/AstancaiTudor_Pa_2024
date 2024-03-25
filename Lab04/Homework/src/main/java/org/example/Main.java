package org.example;

import com.github.javafaker.Faker;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args) {


        Problem p = new Problem();
        p.setRandomGroup();

        p.printDriversSortedByAge();
        p.printPassengersSortedByName();

        p.setDestinationsMap();
        p.printDestinationsMap();

        p.setDriversDestinations();
        p.printDriversDestinations();

        p.allocatePassengerToDrivers();
    }
}

