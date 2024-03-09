package Compulsory;

public class Vehicle
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
