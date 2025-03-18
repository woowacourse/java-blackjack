package blackjack.model.bettings;

public class Wager {
    private static final int INDEX_PLAYER_WAGER = 0;
    private static final double VALUE_TIE = 0;
    private static final double VALUE_NORMAL = 1.0;
    private static final double VALUE_BLACKJACK = 1.5;

    private double wager;

    public Wager(double wager) {
        this.wager = wager;
    }

    public void updatePlayerWager(double multiplier) {
        if (multiplier == VALUE_TIE) {
            return;
        }
        wager += wager * multiplier;
    }

    public void updateDealerWager(double multiplier, double playerWager) {
        if (multiplier == VALUE_BLACKJACK) {
            multiplier = VALUE_NORMAL;
        }
        wager += playerWager * multiplier;
    }

    public double getWager() {
        return wager;
    }
}
