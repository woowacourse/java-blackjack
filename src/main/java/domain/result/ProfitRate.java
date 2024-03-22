package domain.result;

public enum ProfitRate {

    BLACKJACK(1.5),
    WIN(1),
    LOSE(-1),
    PUSH(0);

    private final double rate;

    ProfitRate(final double rate) {
        this.rate = rate;
    }

    public double getValue() {
        return rate;
    }
}
