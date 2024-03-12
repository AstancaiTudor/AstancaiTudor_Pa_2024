import java.time.*;
import java.util.Map;
import java.util.HashMap;

public abstract class Atraction
{
    protected String name;
    protected Duration[] timetable=new Duration[7];


    public Atraction() {}

    public Atraction(String name, Duration[] timeTable) {
        this.name = name;
        this.timetable=timeTable;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Duration[] getTimetable() {
        return timetable;
    }

    public void setTimetable(Duration[] timetable) {
        this.timetable = timetable;
    }

    public String toString()
    {
        StringBuilder aux=new StringBuilder();
        aux.append("Atraction's name: ").append(name);
        aux.append(" has the following timetable: ");
        for (int i = 0; i < timetable.length; i++) {
            if (timetable[i] != null) {
                String day = "";
                switch (i) {
                    case 0:
                        day = "Monday";
                        break;
                    case 1:
                        day = "Tuesday";
                        break;
                    case 2:
                        day = "Wednesday";
                        break;
                    case 3:
                        day = "Thursday";
                        break;
                    case 4:
                        day = "Friday";
                        break;
                    case 5:
                        day = "Saturday";
                        break;
                    case 6:
                        day = "Sunday";
                        break;
                }
                aux.append('\n');
                long hours = timetable[i].toHours();
                long minutes = timetable[i].toMinutes() % 60;
                aux.append(day).append(" Opened for: ");
                aux.append(String.format("%02d:%02d", hours, minutes)).append(" ");
                aux.append(" hours");
            }
        }

        if(this instanceof Payable)
        {
            aux.append('\n');
            aux.append(" and the price of the ticket is: ").append(((Payable) this).getEntryFee());
        }
        aux.append('\n');
        return aux.toString();
    }
}
