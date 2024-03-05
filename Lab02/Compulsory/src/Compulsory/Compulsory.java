package Compulsory;

import java.time.LocalTime;

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
}

class Vehicle
{
    private String name;
    private Depot depot;

    public Vehicle() {}

    public Vehicle(String name)
    {
        this.name = name;
    }

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
        if(this.depot==null)
        {
            return "Vehicle's name:"+ name + " and Depot: No depot ";
        }
        else
            return "Vehicle's name:"+ name + " and Depot:" + depot.getName();
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
}

enum ClientType{ REGULAR, PREMIUM; }

public class Compulsory{
    public static void main(String[] args)
    {
        Client c1 =new Client("Tudor",ClientType.REGULAR);
        c1.setMinTime(LocalTime.of(10,30));
        c1.setMaxTime(LocalTime.of(12,45));
        System.out.println(c1);
        Client c2=new Client("Andrei",ClientType.PREMIUM,LocalTime.of(20,15),LocalTime.of(23,15));
        System.out.println(c2);

        Vehicle v1=new Vehicle();
        v1.setName("Audi");
        Vehicle v2=new Vehicle();
        v2.setName("BMW");
        Depot d1=new Depot();
        d1.setName("Facultate");
        d1.setVehicles(v1,v2);
        System.out.println(d1);

        Vehicle v3=new Vehicle();
        v3.setName("Tesla");
        Vehicle v4=new Vehicle();
        v4.setName("Honda");
        Vehicle v5=new Vehicle("Logan");
        Depot d2=new Depot("Gara",v3,v4);
        System.out.println(d2);
        d2.setVehicles(v5);
        System.out.println(d2);

        Vehicle v6=new Vehicle();
        v6.setName("Renault");
        v6.setDepot(d1);
        System.out.println(v6);

    }
}