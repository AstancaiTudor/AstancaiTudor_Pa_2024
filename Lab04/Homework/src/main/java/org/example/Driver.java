package org.example;

import java.util.ArrayList;
import java.util.List;

/**
 * Driver class is a subclass of Person class
 * @author Tudor
 */
public class Driver extends Person {
    List<Destination> destinations = new ArrayList<Destination>();

    /**
     * Constructor to initialize name, age and destinations
     * @param name , name of the driver
     * @param age , age of the driver
     * @param destinations , list of destinations
     */
    public Driver(String name, int age, List<Destination> destinations) {
        super(name, age);
        this.destinations = destinations;
    }

    /**
     * Getter method for destinations
     * @return list of destinations
     */
    public List<Destination> getDestination() {
        return destinations;
    }


}
