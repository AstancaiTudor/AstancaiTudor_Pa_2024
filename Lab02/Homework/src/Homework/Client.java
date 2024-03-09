package Homework;

import java.time.LocalTime;
import java.util.Objects;

/**
 * Clasa Client implementeaza metode cu care poti seta numele ,tipul si intervalul de timp al clientilor
 * @author Tudor
 */
public class Client
{
    private String name;
    private ClientType type;
    private LocalTime minTime;
    private LocalTime maxTime;

    /**
     * Constructor care atribuie valori campurilor private din clasa Depot , le va atribui numele si vehiculele date de user.
     * @param name Parametrul este de tip String si contine numele clientului
     * @param type Parametrul este un obiect al clasei ClientType
     * @param minTime Parametrul este de tip LocalTime si reprezinta marginea inferioara a intervalului
     * @param maxTime Parametrul este de tip LocalTime si reprezinta marginea superioara a intervalului
     */
    public Client(String name, ClientType type, LocalTime minTime, LocalTime maxTime)
    {
        this.name = name;
        this.type = type;
        this.minTime = minTime;
        this.maxTime = maxTime;
    }

    /**
     * Returneaza numele clientului
     * @return nume client
     */
    public String getName() {
        return name;
    }

    /**
     * Seteaza numele clientului
     * @param name de tip String
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returneaza tipul clientului
     * @return tip client
     */
    public ClientType getType()
    {
        return type;
    }

    /**
     * Seteaza tipul clientului
     * @param type de tip ClientType
     */
    public void setType(ClientType type)
    {
        this.type = type;
    }

    /**
     * Returneaza marginea inferioara a intervalului de timp.
     * @return margine inferioara a intervalului de timp
     */
    public LocalTime getMinTime() {
        return minTime;
    }

    /**
     * Seteaza marginea inferioara a intervalului de timp.
     * @param minTime de tip LocalTime
     */
    public void setMinTime(LocalTime minTime) {
        this.minTime = minTime;
    }

    /**
     * Returneaza marginea superioara a intervalului de timp.
     * @return margine superioara a intervalului de timp
     */
    public LocalTime getMaxTime() {
        return maxTime;
    }

    /**
     * Seteaza marginea superioara a intervalului de timp.
     * @param maxTime de tip LocalTime
     */
    public void setMaxTime(LocalTime maxTime) {
        this.maxTime = maxTime;
    }

    /**
     *  Metoda override in care am facut mesaje custom care sa fie afisate.
     * @return Returneaza un sir de caractere ce contine detalii despre vehiculele sale.
     */
    @Override
    public String toString()
    {
        return "Name:"+ name+" Type:" + type+" minTime:"+minTime+" maxTime:"+maxTime;
    }

    /**
     * Metoda este override si returneaza daca numele a doi clienti sunt identice sau nu.
     * @param obj este de tipul Object
     * @return Returneaza true daca cele doua nume sunt egale, altfel false.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Client client = (Client) obj;
        return Objects.equals(name, client.name);
    }

}
