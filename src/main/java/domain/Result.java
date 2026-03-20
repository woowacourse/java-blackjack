package domain;

public enum Result {
    BLACKJACK(1.5),
    WIN(1),
    LOSE(-1),
    TIE(0);

    private final double profitRate;

    Result(double profitRate) {
        this.profitRate = profitRate;
    }

    public double getProfitRate() {
        return profitRate;
    }
}
