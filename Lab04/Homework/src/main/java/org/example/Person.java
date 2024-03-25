package org.example;

import java.util.List;
/**
 * Person class is an abstract class which is extended by Driver and Passenger classes
 * It has name and age as attributes
 * It has abstract method getDestination which is implemented by Driver and Passenger classes
 * @author Tudor
 */
abstract public class Person
{
    String name;
    int age;

    /**
     * Constructor to initialize name and age
     * @param name , name of the person
     * @param age , age of the person
     */
    public Person(String name,int age)
    {
        this.name = name;
        this.age=age;
    }

    /**
     * Getter method for age
     * @return age of the person
     */
    public int getAge() {
        return age;
    }

    /**
     * Setter method for age
     * @param age , age of the person
     */
    public void setAge(int age) {
        this.age = age;
    }

    /**
     * Getter method for name
     * @return name of the person
     */
    public String getName() {
        return name;
    }

    /**
     * Setter method for name
     * @param name , name of the person
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Abstract method getDestination which is implemented by Driver and Passenger classes
     */
    abstract public List<Destination> getDestination();
}

