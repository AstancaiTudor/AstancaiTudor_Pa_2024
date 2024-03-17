import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Map;

/**
 * Clasa Church care mosteneste clasa Atraction si implementeaza interfata Visitable
 * @author Tudor
 */
public class Church extends Atraction implements Visitable
{
    /**
     * Constructorul pentru clasa Church
     * @param name numele bisericii, de tip String
     * @param timetable orarul bisericii, de tip Map<LocalDate,Pair<LocalTime,LocalTime>>
     */
    public Church(String name, Map<LocalDate,Pair<LocalTime,LocalTime>> timetable) {
        super(name,timetable);
    }
}
