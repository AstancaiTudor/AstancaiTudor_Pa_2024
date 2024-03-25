package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

/**
 * Destination class is used to store the name of the destination
 * @author Tudor
 */
public class Destination
{
    String name;

    /**
     * Constructor to initialize name
     * @param name , name of the destination
     */
    public Destination(String name)
    {
        this.name = name;
    }

    /**
     * Getter method for name
     * @return name of the destination
     */
    public String getName() {
        return name;
    }

    /**
     * toString method to return name of the destination.
     * @return name of the destination
     */
    public String toString() {
        return name;
    }

    /**
     * equals method to compare two destinations
     * @param o , object to be compared
     * @return true if the destinations are equal, false otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Destination that = (Destination) o;
        return Objects.equals(name, that.name);
    }

}
