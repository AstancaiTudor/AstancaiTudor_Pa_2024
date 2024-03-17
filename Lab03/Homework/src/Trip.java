import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Comparator;
import java.util.Map;

/**
 * Clasa Trip care contine un oras, o data de inceput si o data de sfarsit a calatoriei si o lista de atractii.
 * @author Tudor
 */
public class Trip
{
    private String city;
    private LocalDate startTrip;
    private LocalDate endTrip;
    private List<Atraction> atraction =new ArrayList<>();

    /**
     * Constructor pentru clasa Trip.
     * @param city parametru de tip String.
     * @param startTrip parametru de tip LocalDate.
     * @param endTrip parametru de tip LocalDate.
     * @param atractions parametru de tip Atraction.
     */
    public Trip(String city, LocalDate startTrip, LocalDate endTrip, Atraction...atractions) {
        this.city = city;
        this.startTrip = startTrip;
        this.endTrip = endTrip;

        for(Atraction a:atractions)
        {
            atraction.add(a);
           /* atraction.add(new Atraction() {
            } */
        }
    }

    /**
     * returneaza atractiile.
     * @return parametru de tip List<Atraction>.
     */
    public List<Atraction> getAtraction() {
        return atraction;
    }

    /**
     * seteaza atractiile.
     * @param atraction parametru de tip List<Atraction>.
     */
    public void setAtraction(List<Atraction> atraction) {
        this.atraction = atraction;
    }

    /**
     * returneaza sfarsitul vacantei.
     * @return parametru de tip LocalDate.
     */
    public LocalDate getEndTrip() {
        return endTrip;
    }

    /**
     * Seteaza sfarsitul vacantei.
     * @param endTrip parametru de tip LocalDate.
     */
    public void setEndTrip(LocalDate endTrip) {
        this.endTrip = endTrip;
    }

    /**
     * Returneaza data de inceput.
     * @return startTrip parametru de tip LocalDate.
     */
    public LocalDate getStartTrip() {
        return startTrip;
    }

    /**
     * Seteaza data de inceput.
     * @param startTrip parametru de tip LocalDate.
     */
    public void setStartTrip(LocalDate startTrip) {
        this.startTrip = startTrip;
    }

    /**
     * Returneaza orasul.
     * @return city parametru de tip String.
     */
    public String getCity() {
        return city;
    }

    /**
     * Seteaza orasul.
     * @param city parametru de tip String.
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     *  Metoda override in care am facut mesaje custom care sa fie afisate.
     * @return Returneaza un sir de caractere ce contine detalii despre orasul vizitat si atractiile din el.
     */
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

    /**
     * Metoda care afiseaza locatiile care sunt vizitabile dar nu sunt platibile.
     */
    public void printVisitableLocations()
    {
        System.out.println("Locations that are visitable but not payable are: ");
        List<Atraction> visitableLocations = new ArrayList<>();
        for (Atraction a : atraction) {
            if (a instanceof Visitable && !(a instanceof Payable)) {
                visitableLocations.add(a);
            }
        }

        for(Atraction a : atraction)
            if(a instanceof Visitable && !(a instanceof Payable))
            {
                System.out.println(a.getName() + " with the following timetable: ");
                List<Map.Entry<LocalDate, Pair<LocalTime, LocalTime>>> entryList = new ArrayList<>(a.timetable.entrySet());
                entryList.sort(Map.Entry.comparingByValue(Comparator.comparing(Pair :: getFirst)));

                for(Map.Entry<LocalDate, Pair<LocalTime, LocalTime>> entry : entryList)
                    System.out.println(entry.getKey() + " between " + entry.getValue().getFirst() + " and " + entry.getValue().getSecond());
                System.out.println();
            }
    }
}
