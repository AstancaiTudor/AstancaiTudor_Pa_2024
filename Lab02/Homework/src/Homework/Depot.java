package Homework;
import java.util.Arrays;
import java.util.Objects;
import java.util.ArrayList;
import java.util.List;

/**
 * Clasa Depot implementeaza metode cu care poti seta numele depourilor si vehiculele pe care le contin.
 * @author Tudor
 */
public class Depot
{
    private String name;
    private Vehicle[] vehicles;

    /**
     * Aceasta este constructorul care atribuie valori campurilor private din clasa Depot , le va atribui numele si vehiculele date de user.
     * @param name Parametrul este de tip String si contine numele depoului
     * @param vehicles Parametrul este de tip Vehicle , variadic, ceea ce inseamna ca atunci cand apelam putem avea un numar variabil de parametri de acest tip
     */
    public Depot(String name, Vehicle... vehicles)
    {
        this.name = name;
        this.vehicles = vehicles;

        for(Vehicle v : this.vehicles)
            v.setDepot(this);
    }

    /**
     * Returneaza numele depoului
     * @return nume depou
     */
    public String getName() {
        return name;
    }


    /**
     * Seteaza valoare campului name;
     * @param name Parametrul este de tip String si contine numele depoului
     */
    public void setName(String name) {
        this.name = name;
    }


    /**
     * Metoda seteaza vehiculele la depoul curent, prima data verificam ca parametrul dat sa nu fie null.
     * Apoi redimensionam vectorul de vehicule si introducem valorile unice.
     * Apoi creez un nou array si copiez vehiculele din vectorul original , asigurandu-ma ca nu exista dubluri.
     * In final inlocuiesc vectorul original cu cel care contine doar elementele unice si setez depoul vehiculelor din el la cel curent.
     * @param sourceVehicles Parametrul este de tip Vehicle, variadic , si contine vehiculele pe care le introducem in depou.
     */
    public void setVehicles(Vehicle ... sourceVehicles) {
        if (sourceVehicles != null) {
            int uniqueCount = 0;
            vehicles = new Vehicle[sourceVehicles.length];


            for (Vehicle vehicle : sourceVehicles) {   //adaugarea vehiculelor
                if (!containsVehicle(vehicle)) {
                    vehicles[uniqueCount] = vehicle;
                    uniqueCount++;
                }
            }

            Vehicle[] uniqueVehicles = new Vehicle[uniqueCount];
            System.arraycopy(vehicles, 0, uniqueVehicles, 0, uniqueCount);
            vehicles = uniqueVehicles;

            for(Vehicle v:vehicles)
                v.setDepot(this);
        } else {
            System.out.println("There are no vehicles");
        }
    }

    /**
     * Metoda verifica daca exista deja un vehicul in vectorul de vehicule , declarat mai sus.
     * Verificam prima data sa nu fie null si apoi parsam prin obiectele de tip vehicle si facem verificarea de egalitate folosind metoda equals();
     * @param vehicle Parametrul este de tip Vehicle
     * @return Returneaza true daca vehiculul exista deja in vector, respectiv false daca nu.
     */
    private boolean containsVehicle(Vehicle vehicle) {
        if (vehicles != null) {
            for (Vehicle v : vehicles) {
                if (v != null && v.equals(vehicle)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Metoda returneaza vehiculele ,dupa ce le introduce intr-o lista ,verificand sa nu fie null.
     * La sfarsit folosind metoda toArray convertesc inapoi la un tablou de obiecte de tip Vehicle
     * @return un tablou de obiecte Vehicle
     */
    public Vehicle[] getVehicles() {
        List<Vehicle> vehicleList = new ArrayList<>();

        if (vehicles != null && vehicles.length > 0) {
            for (Vehicle v : vehicles) {
                if (v != null) {
                    vehicleList.add(v);
                }
            }
        } else {
            return null;
        }

        return vehicleList.toArray(new Vehicle[0]);
    }

    /**
     *  Metoda override in care am facut mesaje custom care sa fie afisate.
     * @return Returneaza un sir de caractere ce contine detalii despre depozit si vehiculele sale.
     */
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

    /**
     * Metoda este override si returneaza daca numele a doua depouri sunt identice sau nu.
     * @param obj este de tipul Object
     * @return Returneaza true daca cele doua nume sunt egale, altfel false.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Depot depot = (Depot) obj;
        return Objects.equals(name, depot.name) && Arrays.equals(vehicles, depot.vehicles);
    }
}
