import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Map;

/**
 * Clasa Concert care mosteneste clasa Atraction si implementeaza interfetele Visitable si Payable
 */
public class Concert extends Atraction implements Visitable,Payable
{
    private int entryFee;

    /**
     * Constructorul pentru clasa Concert
     * @param name numele concertului, de tip String
     * @param timetable orarul concertului, de tip Map<LocalDate,Pair<LocalTime,LocalTime>>
     * @param entryFee taxa de intrare, de tip int
     */
    public Concert(String name, Map<LocalDate,Pair<LocalTime,LocalTime>> timetable, int entryFee) {
        super(name,timetable);
        this.entryFee = entryFee;
    }

    /**
     * Metoda care returneaza taxa de intrare
     * @return taxa de intrare, de tip int
     */
    public int getEntryFee() {
        return entryFee;
    }

    /**
     * Metoda care seteaza taxa de intrare
     * @param entryFee taxa de intrare, de tip int
     */
    public void setEntryFee(int entryFee) {
        this.entryFee = entryFee;
    }
}
