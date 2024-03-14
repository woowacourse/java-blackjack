package domain.blackjack;

public enum WinStatus {

    WIN(1),
    BLACKJACK(1.5),
    PUSH(0),
    LOSE(-1);

    private final double profitMultiplier;

    WinStatus(double profitMultiplier) {
        this.profitMultiplier = profitMultiplier;
    }

    public static WinStatus from(boolean winStatus) {
        if (winStatus) {
            return WIN;
        }
        return LOSE;
    }

    public double getProfitMultiplier() {
        return profitMultiplier;
    }
}
