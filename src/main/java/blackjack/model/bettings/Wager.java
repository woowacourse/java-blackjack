package blackjack.model.bettings;

public class Wager {
    private static final double VALUE_TIE = 0;
    private static final double VALUE_NORMAL = 1.0;
    private static final double VALUE_BLACKJACK = 1.5;

    private final double wager;

    public Wager(double wager) {
        this.wager = wager;
    }

    public Wager updatePlayerWager(double multiplier) {
        if (multiplier == VALUE_TIE) {
            return new Wager(wager);
        }
        double newWager = wager + wager * multiplier;
        return new Wager(newWager);
    }

    public Wager updateDealerWager(double multiplier, double playerWager) {
        if (multiplier == VALUE_BLACKJACK) {
            multiplier = VALUE_NORMAL;
        }
        double newWager = wager + playerWager * multiplier;
        return new Wager(newWager);
    }

    public double getWager() {
        return wager;
    }
}
