import java.time.*;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.HashMap;

/**
 * Clasa abstracta Atraction care contine numele atractiei si orarul atractiei
 * @author Tudor
 */
public abstract class Atraction
{
    protected String name;
    protected Map<LocalDate,Pair<LocalTime,LocalTime>> timetable=new LinkedHashMap<>();

    public Atraction() {}

    /**
     * Constructorul pentru clasa Atraction
     * @param name numele atractiei, de tip String
     * @param timetable orarul atractiei, de tip Map<LocalDate,Pair<LocalTime,LocalTime>>
     */
    public Atraction(String name,Map<LocalDate,Pair<LocalTime,LocalTime>> timetable) {
        this.name = name;
        this.timetable=timetable;
    }

    /**
     * Metoda care returneaza orarul atractiei
     * @return orarul atractiei, de tip Map<LocalDate,Pair<LocalTime,LocalTime>>
     */
    public Map<LocalDate, Pair<LocalTime, LocalTime>> getTimetable() {
        return timetable;
    }

    /**
     * Metoda care seteaza orarul atractiei
     * @param timetable orarul atractiei, de tip Map<LocalDate,Pair<LocalTime,LocalTime>
     */
    public void setTimetable(Map<LocalDate, Pair<LocalTime, LocalTime>> timetable) {
        this.timetable = timetable;
    }

    /**
     * Metoda care returneaza numele atractiei
     * @return numele atractiei, de tip String
     */
    public String getName() {
        return name;
    }

    /**
     * Metoda care seteaza numele atractiei
     * @param name numele atractiei, de tip String
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     *  Metoda override in care am facut mesaje custom care sa fie afisate.
     * @return Returneaza un sir de caractere ce contine detalii despre o atractie si orarul ei.
     */
    public String toString()
    {
        StringBuilder aux=new StringBuilder();
        aux.append("Atraction's name: ").append(name);
        aux.append(" has the following timetable: ");
        aux.append('\n');

        for (Map.Entry<LocalDate, Pair<LocalTime, LocalTime>> entry : timetable.entrySet()) {
            aux.append("Date: ").append(entry.getKey()).append(", Start time: ").append(entry.getValue().getFirst()).append(", End time: ").append(entry.getValue().getSecond()).append("\n");
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

