package org.example;
import com.github.javafaker.Faker;
import java.security.Permission;
import java.util.*;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Problem class where are implemented the main methods for the problem
 * @author Tudor
 */
public class Problem
{
    Person[] persons = new Person[10];
    List<Destination> destinations = new ArrayList<Destination>();
    List<Destination> randomDestinations = new ArrayList<Destination>();
    List<Destination> driversDestinations = new ArrayList<Destination>();
    List<Person> drivers = new LinkedList<>();
    Set<Person> passengers = new TreeSet<>(Comparator.comparing(Person :: getName));
    Map<Destination,List<Person>> destinationsMap = new HashMap<>();
    Map<String,String> crew = new HashMap<>();

    public Problem() {}

    /**
     * Method to set a random group of persons , using streams and a third party library for generating random data.
     */
     public void setRandomGroup()
    {
        Random random = new Random();

        for (int i = 0; i < 15; i++) {
            destinations.add(new Destination(new Faker().country().name()));
        }

        persons = IntStream.range(1, 10)
                .mapToObj(i->
                {   String name= new Faker().name().fullName();
                    Destination randomDestination = destinations.get(random.nextInt(destinations.size()));
                    randomDestinations = new ArrayList<Destination>();
                    int contor=0;
                    for (Destination d : destinations) {
                        contor++;
                        if (random.nextBoolean() && contor%2==0) {
                            randomDestinations.add(d);
                        }
                    }
                    if(random.nextBoolean())
                        return new Passenger(name, random.nextInt(20,80),randomDestination);
                    else return new Driver(name,   random.nextInt(20,50),randomDestinations);
                }).toArray(Person[]::new);
    }

    /**
     * Method to print the drivers sorted by age
     */
    public void printDriversSortedByAge(){
        for (Person person : persons)
            if (person instanceof Driver)
                drivers.add(person);

        Collections.sort(drivers, Comparator.comparing(Person::getAge));

        System.out.println("Soferii sortati dupa varsta sunt:");
        for (Person driver : drivers) {
            System.out.println(driver.getName() + " " + driver.getAge() + " " + driver.getDestination());
        }
    }

    /**
     * Method to print the passengers sorted by name
     */
    public void printPassengersSortedByName()
    {
        for(Person person: persons)
            if(person instanceof Passenger)
                passengers.add(person);

        System.out.println();
        System.out.println("Pasagerii sortati dupa nume sunt:");
        for(Person passenger: passengers)
        {
            System.out.println(passenger.getName() + " " + passenger.getAge() + " " + passenger.getDestination());
        }
    }

    /**
     * Method to set the drivers destinations, using a set to be sure that there won't be any duplicates
     */
    public void setDriversDestinations()
    {
        Set<Destination> uniqueDestinations = new HashSet<>();
        for(Person driver : drivers)
        {
            List<Destination> driverDestinations = driver.getDestination();
            uniqueDestinations.addAll(driverDestinations);
        }

        driversDestinations.addAll(uniqueDestinations);
    }

    /**
     * Method to set the destinations map, using a map to store the destinations and the persons that visit them
     */
    public void setDestinationsMap() {
        destinationsMap.clear();

        for (Destination destination : destinations) {
            List<Person> personsForDestination = new ArrayList<>();

            for (Person person : persons) {
                List<Destination> personDestinations = person.getDestination();
                if (personDestinations != null && personDestinations.contains(destination)) {
                    personsForDestination.add(person);
                }
            }

            destinationsMap.put(destination, personsForDestination);
        }
    }

    /**
     * Method to print the destinations map
     */
    public void printDestinationsMap()
    {
        System.out.println();
        System.out.println("Destinations map is :");
        for (Map.Entry<Destination, List<Person>> entry : destinationsMap.entrySet()) {
            if(!entry.getValue().isEmpty())
            {
                System.out.println();
                System.out.println("This country: " + entry.getKey() + " is visited by:");

                for(Person person : entry.getValue())
                    System.out.println( person.getName());
            }
        }
    }

    /**
     * Method to print the drivers destinations
     */
    public void printDriversDestinations()
    {
        System.out.println();
        System.out.println("Drivers destinations are:");
        for(Destination d : driversDestinations)
            System.out.println(d.getName());
    }

    /**
     * Method to allocate passengers to drivers based on their destinations
     * It is used a greedy algorithm that it will iterates through the passengers and for each passenger it will iterate through the drivers
     * When a valid driver is found , it is allocated to the passenger and removed from the list of unallocated drivers
     */
    public void allocatePassengerToDrivers()
    {
        List<Person> unallocatedDrivers = new ArrayList<>(drivers); // Inițializăm o copie a listei de șoferi nealocați
        System.out.println();
        for (Person passenger : passengers) {
            List<Destination> passengerDestinations = passenger.getDestination();
            boolean passengerAllocated = false;

            for (Person driver : unallocatedDrivers) {
                List<Destination> driverDestinations = driver.getDestination();
                boolean hasCommonDestination = false;

                for (Destination destination : passengerDestinations) {
                    if (driverDestinations.contains(destination)) {
                        hasCommonDestination = true;
                        break;
                    }
                }

                if (hasCommonDestination) {
                    crew.put(driver.getName(), passenger.getName());
                    unallocatedDrivers.remove(driver);
                    passengerAllocated = true;
                    break;
                }
            }

            if (!passengerAllocated) {
                System.out.println("Nu s-a putut aloca un șofer pentru pasagerul: " + passenger.getName());
            }
        }


        for (Map.Entry<String, String> entry : crew.entrySet()) {
            System.out.println("Driver " + entry.getKey() + "este alocat clientului: " + entry.getValue());
        }
    }
}
