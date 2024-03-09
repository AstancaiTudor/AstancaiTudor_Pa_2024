package Homework;

import java.time.LocalTime;

/**
 * Clasa Vehicle este abstracta si este superclasa pentru clasele Truck si Drone.
 * @author Tudor
 */
public abstract class Vehicle
{
    private String name;
    private Depot depot;
    private LocalTime currentTime;

    /**
     * Aceasta este constructorul care atribuie valori campurilor private din clasa Vehicle , le va atribui numele ,depourile si orele date de user.
     * @param name parametrul este de tip String si contine numele vehiculului
     * @param currentTime parametrul este de tip LocalTime si contine ora de la care vehiculul este disponibil
     */
    public Vehicle(String name,LocalTime currentTime) {this.name=name; this.currentTime=currentTime;}

    /**
     * Returneaza ora.
     * @return ora
     */
    public LocalTime getCurrentTime() {
        return currentTime;
    }

    /**
     * Seteaza ora.
     * @param currentTime de tip LocalTime
     */
    public void setCurrentTime(LocalTime currentTime) {
        this.currentTime = currentTime;
    }

    /**
     * Seteaza numele vehiculului
     * @param name de tip String
     */
    public void setName(String name)
    {
        this.name=name;
    }

    /**
     * Returneaza numele vehiculului
     * @return nume vehicul
     */
    public String getName()
    {
        return name;
    }

    /**
     * Returneaza numele depoului
     * @return nume depou
     */
    public Depot getDepot() {
        return depot;
    }

    /**
     * Seteaza numele depoului
     * @param depot de tip Depot
     */
    public void setDepot(Depot depot) {
        if(name!=null)
            this.depot = depot;
    }

    /**
     *  Metoda override in care am facut mesaje custom care sa fie afisate, am implementat pe doua ramuri , una pentru Truck si una pentru Drone.
     * @return Returneaza un sir de caractere ce contine detalii despre vehiculele sale.
     */
    @Override
    public String toString()
    {
        if(this instanceof Truck)
        {
            if (this.depot == null) {
                return "Truck's name:" + name + " and Depot: No depot ";
            } else
                return "Trucks's name:" + name + " and Depot:" + depot.getName();
        }
        else if(this instanceof Drone)
        {
            if (this.depot == null) {
                return "Drone's name:" + name + " and Depot: No depot ";
            } else
                return "Drone's name:" + name + " and Depot:" + depot.getName();
        }
        return "";
    }

    /**
     * Metoda este override si returneaza daca numele a doua vehicule sunt identice sau nu.
     * @param obj este de tipul Object
     * @return Returneaza true daca cele doua nume sunt egale, altfel false.
     */
    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof Vehicle)) {
            return false;
        }
        Vehicle other = (Vehicle) obj;
        return name.equals(other.name);
    }

}

/**
 * Clasa Truck mosteneste clasa abstracta Vehicle,impreuna cu toate metodele sale , insa are cateva featuri noi
 */
class Truck extends Vehicle
{
    private int capacity;

    /**
     * Constructor , seteaza numele vehiculului , ora de la care e disponibil si capacitatea acestuia
     * @param name de tip String reprezentand numele vehiculului
     * @param currentTime de tip LocalTime, reprezentand ora
     * @param capacity de tip int , reprezentand capacitatea vehiculului
     */
    public Truck(String name,LocalTime currentTime,int capacity)
    {
        super(name,LocalTime.of(0,0));
        this.capacity = capacity;
    }

    /**
     * Returneaza capacitatea camionului
     * @return capacitatea
     */
    public int getCapacity() {
        return capacity;
    }

    /**
     * Seteaza capacitatea camionului
     * @param capacity de tip int
     */
    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }
}

/**
 * Clasa Drone mosteneste clasa abstracta Vehicle,impreuna cu toate metodele sale , insa are cateva featuri noi
 */
class Drone extends Vehicle
{
    private int maximumFlightDuration;

    /**
     * Constructor , seteaza numele dronei , ora de la care e disponibila si durata de zbor a acesteia
     * @param name de tip String reprezentand numele dronei
     * @param currentTime de tip LocalTime, reprezentand ora
     * @param maximumFlightDuration de tip int , reprezentand durata de zbor a acesteia
     */
    public Drone(String name,LocalTime currentTime,int maximumFlightDuration) {
        super(name,LocalTime.of(0,0));
        this.maximumFlightDuration = maximumFlightDuration;
    }

    /**
     * Returneaza durata de zbor a dronei
     * @return durata de zbor
     */
    public int getMaximumFlightDuration() {
        return maximumFlightDuration;
    }

    /**
     * Seteaza durata de zbor a dronei
     * @param maximumFlightDuration de tip int
     */
    public void setMaximumFlightDuration(int maximumFlightDuration) {
        this.maximumFlightDuration = maximumFlightDuration;
    }
}