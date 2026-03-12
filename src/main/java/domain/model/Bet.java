package domain.model;

public class Bet {
    private final int betAmount;

    public Bet(int betAmount) {
        this.betAmount = betAmount;
    }

    public static Bet of(int betAmount) {
        return new Bet(betAmount);
    }

    public int getBetAmount() {
        return betAmount;
    }
}
