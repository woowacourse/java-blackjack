package blackjack.domain.participant;

public class Player extends Participant {
    private final int betting;
    private double rate;
    private final Name name;
    public Player(final Name name, final int betting) {
        super();
        this.name = name;
        this.betting = betting;
    }

    public int getBetting() {
        return this.betting;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }
    public String getName() {
        return name.getValue();
    }
    public int getRevenue() {
        return (int) (rate * betting);
    }
}
