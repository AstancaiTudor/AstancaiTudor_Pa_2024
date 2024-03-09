package Compulsory;

import java.time.LocalTime;

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