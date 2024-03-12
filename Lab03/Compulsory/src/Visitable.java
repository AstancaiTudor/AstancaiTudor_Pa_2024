import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalTime;

public interface Visitable
{
    Duration[] getTimetable();
    void setTimetable(Duration[] timetable);
    String getName();
    void setName(String name);

}
