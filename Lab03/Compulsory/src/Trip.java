import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;

public class Trip
{
    private String city;
    private DayOfWeek startTrip, endTrip;
    private List<Atraction> atraction =new ArrayList<>();

    public Trip(String city, DayOfWeek startTrip, DayOfWeek endTrip, Atraction...atractions) {
        this.city = city;
        this.startTrip = startTrip;
        this.endTrip = endTrip;

        for(Atraction a:atractions)
        {
            //atraction.add(a);
           /* atraction.add(new Atraction() {
            } */
        }
    }

    public DayOfWeek getEndTrip() {
        return endTrip;
    }

    public void setEndTrip(DayOfWeek endTrip) {
        this.endTrip = endTrip;
    }

    public DayOfWeek getStartTrip() {
        return startTrip;
    }

    public void setStartTrip(DayOfWeek startTrip) {
        this.startTrip = startTrip;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String toString()
    {
        StringBuilder aux=new StringBuilder();
        aux.append("City's name is: ").append(city);
        aux.append('\n');
        aux.append(" The trip is between ").append(startTrip).append(" and ").append(endTrip);
        aux.append('\n');
        aux.append(" The atractions are: ");
        aux.append('\n');
        for(Atraction a : atraction)
        {
            aux.append(a.getName());
            aux.append('\n');
        }
        return aux.toString();
    }
}
