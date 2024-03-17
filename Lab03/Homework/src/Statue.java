import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Map;

/**
 * Clasa Statue care mosteneste clasa Atraction si implementeaza interfata Visitable
 * @author Tudor
 */
public class Statue extends Atraction implements Visitable
{
    /**
     * Constructorul pentru clasa Statue
     * @param name numele statuii, de tip String
     * @param timetable orarul statuii, de tip Map<LocalDate,Pair<LocalTime,LocalTime>>
     */
    public Statue(String name, Map<LocalDate,Pair<LocalTime,LocalTime>> timetable) {
        super(name,timetable);
    }
}
