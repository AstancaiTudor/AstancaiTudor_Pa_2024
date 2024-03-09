package Homework;

import java.time.LocalTime;

/**
 *  Clasa problem implementeaza metode prin care pot fi adaugati clienti ,depouri si se poate genera matricea de distante intre locatii.
 * @author Tudor
 */
public class Problem
{
    private Depot[] depots = new Depot[10];
    private Client[] clients=new Client[10];
    private int contorDepots=0,contorClients=0;
    private LocalTime[][] distances; // Matricea pentru stocarea distanțelor

    /**
     * Returneaza toate vehiculele , din toate depourile
     * @return vehicule din depouri
     */
    public Vehicle[] getVehicles()
    {
        Vehicle[] vehicles=new Vehicle[30];
        for(int i=0;depots[i]!=null;i++)
        {
            if(depots[i].getName()!=null)
            {
                vehicles=depots[i].getVehicles();
            }
        }
        return vehicles;
    }

    /**
     * Returneaza clientii
     * @return clientii
     */
    public Client[] getClients() {
        return clients;
    }

    /**
     * Seteazae clientii
     * @param clients de tip Client
     */
    public void setClients(Client[] clients) {
        this.clients = clients;
    }

    /**
     * Metoda care adauga cate un depou si care verifica sa nu adauge dubluri.
     * @param depot de tip Depot
     */
    public void addDepot(Depot depot)
    {
        boolean found = false;
        for(int i=0;depots[i]!=null;i++) {
            if (this.depots[i].equals(depot))
                found = true;
        }
        if(!found)
            this.depots[contorDepots++]=depot;
    }

    /**
     * Metoda care adauga cate un client si care verifica sa nu adauge dubluri.
     * @param client de tip Client
     */
    public void addClient(Client client) {
        boolean found = false;
        for (int i = 0; i < contorClients; i++) {
            if (clients[i] != null && clients[i].equals(client)) {
                found = true;
                break;
            }
        }
        if (!found) {
            this.clients[contorClients++] = client;
        }
    }

    /**
     *  Metoda override in care am facut mesaje custom care sa fie afisate, pentru clienti si vehicule.
     * @return Returneaza un sir de caractere ce contine detalii despre vehicule si clienti.
     */
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();

        result.append("The clients are:\n");
        for (Client c : clients) {
            if (c != null) {
                result.append(c.getName()).append(" ");
            }
        }

        Vehicle[] vehicles = getVehicles();
        if (vehicles != null && vehicles.length > 0) {
            result.append("\nThe vehicles are:\n");
            for (Vehicle v : vehicles) {
                if (v != null) {
                    result.append(v).append("\n");
                }
            }
        } else {
            result.append("\nThere are no vehicles\n");
        }

        return result.toString();
    }

    /**
     * Metoda genereaza distantele dintre depou-depou, depou-client si client-client, random.
     * Matricea este generata simetric.
     */
    public void generateAndSetDistances() {
        distances = new LocalTime[depots.length + clients.length][depots.length + clients.length];

        for (int i = 0; i < contorDepots; i++) {
            for (int j = 0; j < contorDepots; j++) {
                if (i != j) {
                    // Generez valori pentru distanțele între depouri
                    distances[i][j] = generateRandomDistance();
                    distances[j][i] = distances[i][j];
                }
            }
        }

        for(int i = 0; i < contorDepots; i++) {
            for (int j = 0; j < contorClients; j++) {
                // Generez valori pentru distanțele între depou și client
                distances[i][contorDepots + j] = generateRandomDistance();
                distances[contorDepots + j][i] = distances[i][contorDepots + j];
            }
        }

        for(int i = 0; i < contorClients; i++) {
            for (int j = i + 1; j < contorClients; j++) {
                // Generez valori pentru distanțele între clienți
                distances[contorDepots + i][contorDepots + j] = generateRandomDistance();
                distances[contorDepots + j][contorDepots + i] = distances[contorDepots + i][contorDepots + j];
            }
        }
    }

    /**
     * Metoda care genereaza random distante.
     * @return o ora random.
     */
    private LocalTime generateRandomDistance() {

        int hour = (int) (Math.random() * 10) + 1;
        return LocalTime.of(hour, 0);
    }

    /**
     * Metoda pentru a accesta distantele dintre locatii
     * @return o matrice de tipul LocalTime;
     */
    public LocalTime[][] getDistances() {
        return distances;
    }
}
