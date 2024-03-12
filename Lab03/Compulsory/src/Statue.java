import java.time.Duration;

public class Statue extends Atraction implements Visitable
{
    public Statue(String name, Duration[] openTime) {
        super(name, openTime);
    }
}
