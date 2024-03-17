import java.time.LocalDate;
import java.time.LocalTime;
import java.util.LinkedHashMap;
import java.util.Map;


public class Main{
    public static void main(String[] args) {

        Map<LocalDate,Pair<LocalTime,LocalTime>> timetable1=new LinkedHashMap<>();

        timetable1.put(LocalDate.of(2024, 3, 12), new Pair<>(LocalTime.of(8, 0), LocalTime.of(17, 0)));
        timetable1.put(LocalDate.of(2024, 3, 13), new Pair<>(LocalTime.of(8, 0), LocalTime.of(17, 0)));
        timetable1.put(LocalDate.of(2024, 3, 14), new Pair<>(LocalTime.of(7, 0), LocalTime.of(17, 0)));
        timetable1.put(LocalDate.of(2024, 3, 15), new Pair<>(LocalTime.of(7, 0), LocalTime.of(17, 0)));
        timetable1.put(LocalDate.of(2024, 3, 16), new Pair<>(LocalTime.of(7, 0), LocalTime.of(17, 0)));
        timetable1.put(LocalDate.of(2024, 3, 17), new Pair<>(LocalTime.of(10, 0), LocalTime.of(13, 0)));
        timetable1.put(LocalDate.of(2024, 3, 18), new Pair<>(LocalTime.of(10, 0), LocalTime.of(13, 0)));

        Map<LocalDate,Pair<LocalTime,LocalTime>> timetable2=new LinkedHashMap<>();

        timetable2.put(LocalDate.of(2024, 7, 20), new Pair<>(LocalTime.of(7, 0), LocalTime.of(15, 0)));
        timetable2.put(LocalDate.of(2024, 7, 21), new Pair<>(LocalTime.of(7, 0), LocalTime.of(15, 0)));
        timetable2.put(LocalDate.of(2024, 7, 22), new Pair<>(LocalTime.of(6, 0), LocalTime.of(16, 0)));
        timetable2.put(LocalDate.of(2024, 7, 23), new Pair<>(LocalTime.of(6, 0), LocalTime.of(16, 0)));
        timetable2.put(LocalDate.of(2024, 7, 24), new Pair<>(LocalTime.of(6, 0), LocalTime.of(17, 0)));
        timetable2.put(LocalDate.of(2024, 7, 25), new Pair<>(LocalTime.of(10, 0), LocalTime.of(13, 0)));
        timetable2.put(LocalDate.of(2024, 7, 26), new Pair<>(LocalTime.of(10, 0), LocalTime.of(13, 0)));

        Church church1=new Church("Sfintii 3 Ierarhi", timetable1);
        Statue statue1=new Statue("Mihai Viteazul",timetable1);
        Concert concert1=new Concert("Untold",timetable1,300);

        //System.out.println(statue1);
      //  System.out.println(church1);
       // System.out.println(concert1);

        Church church2=new Church("Saint Pietro",timetable2);
        Statue statue2=new Statue("Columna lui Traian",timetable2);
        Concert concert2=new Concert("TomorrowLand",timetable2,500);

        //System.out.println(statue2);
      //  System.out.println(church2);
        //System.out.println(concert2);

        Trip trip1=new Trip("Cluj",LocalDate.of(2024,3,13),LocalDate.of(2024,3,17),church1,statue1,concert1);
       // System.out.println(trip1);

        Trip trip2=new Trip("Rome",LocalDate.of(2024,5,13),LocalDate.of(2024,5,17),church2,statue2,concert2);
      //  System.out.println(trip2);

        //trip1.printVisitableLocations();
        //System.out.println(church2.getOpeningHours(LocalDate.of(2024,7,21)));

        TravelPlan plan=new TravelPlan(trip1,trip2);
        System.out.println(plan);
    }
}

