import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Clasa TravelPlan care contine o lista de calatorii.
 * @author Tudor
 */
public class TravelPlan
{
    private List<Trip> plan =new ArrayList<>();

    /**
     * Constructor pentru clasa TravelPlan.
     * @param trips parametru de tip Trip.
     */
    public TravelPlan(Trip...trips)
    {
        for(Trip t:trips)
            plan.add(t);
    }

    /**
     *  Metoda override in care am facut mesaje custom care sa fie afisate.
     * @return Returneaza un sir de caractere ce contine detalii despre ce poate vizita un turist intr-o anumita perioada.
     */
    public String toString()
    {
        StringBuilder aux=new StringBuilder();

        for(Trip t:plan)
        {
            for(Atraction a :t.getAtraction())
            {
                long randomDay = ThreadLocalRandom.current().nextLong(t.getStartTrip().toEpochDay(),t.getEndTrip().toEpochDay());
                LocalDate day=LocalDate.ofEpochDay(randomDay);
                if(a.getTimetable().containsKey(day))
                {
                    aux.append("Turistul viziteaza: ");
                    aux.append(a.getName()).append(" In ziua ").append(day).append(" ");
                    aux.append(" ");
                    aux.append('\n');
                }
                else {
                    aux.append("Turistul nu poate vizita: ");
                    aux.append(a.getName()).append(" In ziua ").append(day).append(" ");
                    aux.append(" ");
                    aux.append('\n');
                }

            }
        }
        return aux.toString();
    }
}
