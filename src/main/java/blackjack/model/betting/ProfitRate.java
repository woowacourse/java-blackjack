package blackjack.model.betting;

public enum ProfitRate {
    PLUS_150_PERCENT(1.5),
    PLUS_100_PERCENT(1),
    ZERO(0),
    MINUS_100_PERCENT(-1)
    ;

    private final double rate;

    ProfitRate(final double rate) {
        this.rate = rate;
    }

    public double getRate() {
        return rate;
    }
}
