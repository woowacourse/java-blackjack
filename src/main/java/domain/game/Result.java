package domain.game;

public enum Result {
    BLACKJACK_WIN(1.5),
    WIN(1.0),
    LOSE(-1.0),
    TIE(0.0);

    private final double profitRate;

    Result(double profitRate) {
        this.profitRate = profitRate;
    }

    public double getProfitRate() {
        return profitRate;
    }
}
