package domain.blackjack;

public enum WinStatus {

    WIN(1.0),
    LOSE(-1),
    PUSH(0);

    private final double betMultiplier;

    WinStatus(double betMultiplier) {
        this.betMultiplier = betMultiplier;
    }

    public Double getBetMultiplier() {
        return betMultiplier;
    }
}
