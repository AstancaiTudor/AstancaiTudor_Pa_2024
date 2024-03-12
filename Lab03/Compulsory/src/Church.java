import java.time.Duration;

public class Church extends Atraction implements Visitable
{
    public Church(String name, Duration[] openTime) {
        super(name, openTime);
    }
}
