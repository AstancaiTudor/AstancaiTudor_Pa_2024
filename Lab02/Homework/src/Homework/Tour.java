package Homework;

import java.time.LocalTime;
import java.util.List;
import java.util.ArrayList;

/**
 * Clasa Tour contine obiecte de tip Vehicle si Client, pentru a le asigna
 * @author Tudor
 */
public class Tour
{
    private Vehicle vehicle;
    private List<Client> clients;

    /**
     * Constructor care atribuie valori vehiculelor.
     * @param vehicle
     */
    public Tour(Vehicle vehicle) {
        this.vehicle = vehicle;
        this.clients = new ArrayList<>();
    }


    /**
     * Metoda in care se adauga cate un client in lista de clienti
     * @param client de tip Client
     */
    public void addClient(Client client) {
        clients.add(client);
    }

}
