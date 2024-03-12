import java.time.Duration;

public class Concert extends Atraction implements Visitable,Payable
{
    private int entryFee;

    public Concert(String name, Duration[] openTime, int entryFee) {
        super(name, openTime);
        this.entryFee = entryFee;
    }

    public int getEntryFee() {
        return entryFee;
    }

    public void setEntryFee(int entryFee) {
        this.entryFee = entryFee;
    }
}
