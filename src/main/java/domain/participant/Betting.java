package domain.participant;

public class Betting {
    private final int betting;

    public Betting(int betting) {
        this.betting = betting;
    }

    public int getBettingMoney(){
        return betting;
    }
}
