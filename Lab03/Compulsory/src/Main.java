import java.sql.SQLOutput;
import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;

public class Main{
    public static void main(String[] args) {

        Duration[] timetable=new Duration[7];
        timetable[0]=Duration.between(LocalTime.of(8,30),LocalTime.of(17,30));
        timetable[1]=Duration.between(LocalTime.of(8,30),LocalTime.of(17,30));
        timetable[2]=Duration.between(LocalTime.of(8,30),LocalTime.of(17,30));
        timetable[3]=Duration.between(LocalTime.of(8,30),LocalTime.of(17,30));
        timetable[4]=Duration.between(LocalTime.of(8,30),LocalTime.of(17,30));
        timetable[5]=Duration.between(LocalTime.of(8,30),LocalTime.of(13,30));
        timetable[6]=Duration.between(LocalTime.of(8,30),LocalTime.of(13,30));


        Church church=new Church("Sfintii 3 Ierarhi", timetable);
        Statue statue=new Statue("Mihai Viteazul",timetable);
        Concert concert=new Concert("Untold",timetable,300);

        System.out.println(statue);
        System.out.println(church);
        System.out.println(concert);


        Trip trip=new Trip("Cluj",DayOfWeek.MONDAY,DayOfWeek.FRIDAY,church,statue,concert);
        System.out.println(trip);
    }
}

