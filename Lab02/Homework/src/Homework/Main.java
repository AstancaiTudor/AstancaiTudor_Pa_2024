package Homework;

import java.time.LocalTime;
import java.util.Arrays;
import java.util.Objects;

class Problem
{
    private Depot[] depots;
    private Client[] clients;
}

class Depot
{
    private String name;
    private Vehicle[] vehicles;

    public Depot() {}

    public Depot(String name, Vehicle... vehicles)
    {
        this.name = name;
        this.vehicles = vehicles;

        for(Vehicle v : this.vehicles)
            v.setDepot(this);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setVehicles(Vehicle ... vehicles)
    {
        this.vehicles = vehicles;
        for(Vehicle v : vehicles) {
            v.setDepot(this);
        }
    }

    public StringBuilder getVehicles()
    {
        StringBuilder auxVehicles =new StringBuilder();
        for(Vehicle v : vehicles)
        {
            auxVehicles.append(v);
            auxVehicles.append("\n");
        }
        return auxVehicles;
    }

    @Override
    public String toString()
    {
        StringBuilder aux = new StringBuilder();
        aux.append("Depot's name:");
        aux.append(name);
        aux.append("\n");
        aux.append("The vehicles from this depot are:");
        for(Vehicle v:vehicles)
        {
            aux.append(v.getName());
            aux.append(" ");
        }
        return aux.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Depot depot = (Depot) obj;
        return Objects.equals(name, depot.name) && Arrays.equals(vehicles, depot.vehicles);
    }
}

abstract class Vehicle
{
    private String name;
    private Depot depot;

    public Vehicle() {}

    public Vehicle(String name) {this.name=name;}

    public void setName(String name)
    {
        this.name=name;
    }

    public String getName()
    {
        return name;
    }

    public Depot getDepot() {
        return depot;
    }

    public void setDepot(Depot depot) {
        this.depot = depot;
    }

    @Override
    public String toString()
    {
        return "Vehicle's name:"+ name + " and Depot:" + depot.getName();
    }

    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof Vehicle)) {
            return false;
        }
        Vehicle other = (Vehicle) obj;
        return name.equals(other.name);
    }

}

class Drone extends Vehicle
{
    private int capacity;

    public Drone(int capacity) {
        this.capacity = capacity;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }
}

class Truck extends Vehicle
{
    private int maximumFlightDuration;

    public Truck(int maximumFlightDuration) {
        this.maximumFlightDuration = maximumFlightDuration;
    }

    public int getMaximumFlightDuration() {
        return maximumFlightDuration;
    }

    public void setMaximumFlightDuration(int maximumFlightDuration) {
        this.maximumFlightDuration = maximumFlightDuration;
    }
}

class Client
{
    private String name;
    private ClientType type;
    private LocalTime minTime;
    private LocalTime maxTime;

    public Client() {}

    public Client(String name, ClientType type)
    {
        this.name=name;
        this.type=type;
    }

    public Client(String name, ClientType type, LocalTime minTime, LocalTime maxTime)
    {
        this.name = name;
        this.type = type;
        this.minTime = minTime;
        this.maxTime = maxTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ClientType getType()
    {
        return type;
    }

    public void setType(ClientType type)
    {
        this.type = type;
    }

    public LocalTime getMinTime() {
        return minTime;
    }

    public void setMinTime(LocalTime minTime) {
        this.minTime = minTime;
    }

    public LocalTime getMaxTime() {
        return maxTime;
    }

    public void setMaxTime(LocalTime maxTime) {
        this.maxTime = maxTime;
    }

    @Override
    public String toString()
    {
        return "Name:"+ name+" Type:" + type+" minTime:"+minTime+" maxTime:"+maxTime;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Client client = (Client) obj;
        return Objects.equals(name, client.name);
    }

}

enum ClientType{ REGULAR, PREMIUM; }

public class Main{
    public static void main(String[] args)
    {
        Client c1 =new Client("Tudor",ClientType.REGULAR);
        c1.setMinTime(LocalTime.of(10,30));
        c1.setMaxTime(LocalTime.of(12,45));
        System.out.println(c1);
        Client c2=new Client("Andrei",ClientType.PREMIUM,LocalTime.of(20,15),LocalTime.of(23,15));
        System.out.println(c2);


        String n= new String("abc");
        System.out.println(n);

    }
}