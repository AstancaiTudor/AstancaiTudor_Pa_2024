package org.example;

import java.util.ArrayList;
import java.util.List;

/**
 * Passenger class is a subclass of Person class
 * @author Tudor
 */
public class Passenger extends Person
{
    List<Destination> destination;

    /**
     * Constructor to initialize name, age and destination
     * @param name , name of the passenger
     * @param age , age of the passenger
     * @param destination , destination of the passenger
     */
    public Passenger(String name,int age,Destination destination)
    {
        super(name,age);
        this.destination=new ArrayList<>();
        this.destination.add(destination);
    }

    /**
     * Getter method for destination
     * @return passenger's destination
     */
    public List<Destination> getDestination() {
        return destination;
    }
}
