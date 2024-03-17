import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Map;

/**
 * Interfata Visitable care contine metoda default getOpeningHours
 * @author Tudor
 */
public interface Visitable
{
    Map<LocalDate, Pair<LocalTime, LocalTime>> getTimetable();
    void setTimetable(Map<LocalDate, Pair<LocalTime, LocalTime>> timetable);
    String getName();
    void setName(String name);

    /**
     * Metoda default getOpeningHours care returneaza ora de deschidere a obiectului de tip Visitable
     * @param date data pentru care se doreste ora de deschidere, de tip LocalDate
     * @return ora de deschidere, de tip LocalTime
     */
    default LocalTime getOpeningHours(LocalDate date)
    {
        Map<LocalDate, Pair<LocalTime, LocalTime>> timetable = getTimetable();
        if (timetable.containsKey(date)) {
            System.out.println("Ora de deschidere este:");
            return timetable.get(date).getFirst();
        } else {
            System.out.println("Nu exista data aceasta");
            return null;
        }
    }

}
