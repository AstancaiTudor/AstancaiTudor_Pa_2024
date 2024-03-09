package Homework;

import java.time.LocalTime;

/**
 * Clasa Solution contine implementarea algoritmului Greedy de atribuire a vehiculelor catre clienti
 * @author Tudor
 */
public class Solution
{
    private Problem problem;

    /**
     * Returneaza obiectul de tip problem.
     * @return problem
     */
    public Problem getProblem() {
        return problem;
    }

    /**
     * Seteaza o valoare de tip Problem obiectului problem.
     * @param problem de tip Problem
     */
    public void setProblem(Problem problem) {
        this.problem = problem;
    }

    /**
     * Alocă clienții vehiculelor pe baza distanțelor. (greedy)
     * Pentru fiecare vehicul, această metodă găsește cel mai apropiat client disponibil și îl atribuie vehiculului.
     * Afișează detaliile alocării pentru fiecare client și vehicul.
     * Dacă nu există vehicule sau clienți disponibili, afișează un mesaj indicând indisponibilitatea.
     */
    public void allocateClientsToVehicles() {
        Vehicle[] vehicles = problem.getVehicles();
        Client[] clients = problem.getClients();
        LocalTime[][] distances = problem.getDistances();

        if (vehicles != null && clients != null && distances != null) {
            boolean[] assigned = new boolean[clients.length];

            for (Vehicle vehicle : vehicles) {
                LocalTime minDistance = null;
                int minIndexClient = -1; 

                for (int i = 0; i < clients.length; i++) {   // Caut cel mai apropiat client disponibil pentru vehiculul curent
                    if (clients[i] != null && !assigned[i]) {
                        if (minDistance == null || distances[i][vehicles.length] != null && distances[i][vehicles.length].compareTo(minDistance) < 0) {
                            minDistance = distances[i][vehicles.length];
                            minIndexClient = i;
                        }
                    }
                }


                if (minIndexClient != -1) { //Asignez clientul celui mai apropiat vehicul
                    if (clients[minIndexClient] != null) {
                        assigned[minIndexClient] = true;
                        System.out.println("Client " + clients[minIndexClient].getName() + " allocated to Vehicle " + vehicle.getName() +
                                " with distance " + minDistance);
                    } else {
                        System.out.println("Client at index " + minIndexClient + " is null. Moving to the next available client.");
                    }
                }
            }
        } else {
            System.out.println("No vehicles or clients available!");
        }
    }
}
