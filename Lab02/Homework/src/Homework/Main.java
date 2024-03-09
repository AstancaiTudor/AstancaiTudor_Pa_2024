package Homework;

import java.time.LocalTime;
import java.util.Arrays;
import java.util.Objects;


public class Main{
    public static void main(String[] args)
    {
        Client c1 =new Client("Tudor",ClientType.REGULAR,LocalTime.of(10,30),LocalTime.of(12,45));
        Client c2=new Client("Andrei",ClientType.PREMIUM,LocalTime.of(20,15),LocalTime.of(23,15));
        Client c3=new Client("Marius",ClientType.REGULAR,LocalTime.of(18,15),LocalTime.of(21,15));
        Client c4=new Client("Marcu",ClientType.REGULAR,LocalTime.of(10,15),LocalTime.of(19,15));


        Truck truck1=new Truck("Volvo",LocalTime.of(0,0),20);
        Drone drone2=new Drone("Lenovo",LocalTime.of(0,0),25);
        Drone drone3=new Drone("Samsung",LocalTime.of(0,0),35);
        Drone drone4=new Drone("Eboda",LocalTime.of(0,0),25);

        Depot[] d1 = {new Depot("Gara"), new Depot("Facultate"),new Depot ("Gara"),new Depot("Ast"),new Depot("Ma")};
        Depot d2 =new Depot("Delta");

        d2.setVehicles(truck1,drone2,drone3,drone4);

        Problem p=new Problem();
        p.addDepot(d1[0]);
        p.addDepot(d1[1]);
        p.addDepot(d2);

        p.addClient(c1);
        p.addClient(c2);
        p.addClient(c3);
        p.addClient(c4);

        p.generateAndSetDistances();
        System.out.println(p);

        Tour tour=new Tour(drone2);
        tour.addClient(c1);
        tour.addClient(c2);
        tour.addClient(c3);
        tour.addClient(c4);

        Solution solution = new Solution();
        solution.setProblem(p);
        solution.allocateClientsToVehicles();
    }
}