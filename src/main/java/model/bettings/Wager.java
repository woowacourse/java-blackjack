package model.bettings;

public class Wager {
    private static final int VALUE_ZERO = 0;

    private double wager;

    public Wager(double wager) {
        this.wager = wager;
    }

    public void updateWager(boolean isDealer, double multiplier, double... playerWager) {
        if (isDealer) {
            updateDealerWager(multiplier, playerWager[VALUE_ZERO]);
            return;
        }
        updatePlayerWager(multiplier);
    }

    private void updatePlayerWager(double multiplier) {
        if (multiplier == VALUE_ZERO) {
            return;
        }
        wager += wager * multiplier;
    }

    private void updateDealerWager(double multiplier, double playerWager) {
        wager += playerWager * multiplier;
    }

    public double getWager() {
        return wager;
    }
}
