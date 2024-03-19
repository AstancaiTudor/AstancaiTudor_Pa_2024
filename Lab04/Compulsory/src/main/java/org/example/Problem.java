package org.example;

import java.security.Permission;
import java.util.*;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Problem
{
    Person[] persons = new Person[10];

    public Problem() {}

    public void setRandomGroup()
    {
        Random random = new Random();
        String[] names = {"Constantin", "Ema", "Mihai", "Sofia", "Sorina", "Olivia", "Paul", "Bogdan", "Vlad", "Andrei"};
        List<String> shuffledNames = Arrays.asList(names);
        Collections.shuffle(shuffledNames);

         persons = IntStream.range(1, 10)
                .mapToObj(i->
                {   String name= shuffledNames.get(i-1);
                    if(random.nextBoolean())
                        return new Passenger(name, random.nextInt(20,80));
                    else return new Driver(name,   random.nextInt(20,50));
                }).toArray(Person[]::new);
    }
    public void printDrivers()
    {
        System.out.println("Soferii sunt:");
        Stream.of(persons).forEach(person -> {if (person instanceof Driver) System.out.println(person.getName());});
    }

    public void printPassengers()
    {
        System.out.println("Pasagerii sunt:");
        Stream.of(persons)
                .filter(person -> person instanceof Passenger)
                .forEach(person -> System.out.println(person.getName()));    }

    public void printDriversSortedByAge(){
        List<Person> drivers = new LinkedList<>();
        for (Person person : persons)
            if (person instanceof Driver)
                drivers.add(person);

        Collections.sort(drivers, Comparator.comparing(Person::getAge));

        System.out.println("Soferii sortati dupa nume sunt:");
        for (Person driver : drivers) {
            System.out.println(driver.getName() + " " + driver.getAge());
        }
    }

    public void printPassengersSortedByName()
    {
        Set<Person> passengers = new TreeSet<>(Comparator.comparing(Person :: getName));
        for(Person person: persons)
            if(person instanceof Passenger)
                passengers.add(person);


        System.out.println("Pasagerii sortati dupa varsta sunt:");
        for(Person passenger: passengers)
        {
            System.out.println(passenger.getName() + " " + passenger.getAge());
        }
    }

}
