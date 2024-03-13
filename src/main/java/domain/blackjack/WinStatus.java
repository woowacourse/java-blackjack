package domain.blackjack;

public enum WinStatus {

    WIN(1),
    LOSE(-1);

    private final int profitMultiplier;

    WinStatus(int profitMultiplier) {
        this.profitMultiplier = profitMultiplier;
    }

    public static WinStatus from(boolean winStatus) {
        if (winStatus) {
            return WIN;
        }
        return LOSE;
    }

    public int getProfitMultiplier() {
        return profitMultiplier;
    }
}
