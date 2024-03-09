package Compulsory;

public class Depot
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
