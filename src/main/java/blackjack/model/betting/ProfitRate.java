package blackjack.model.betting;

public enum ProfitRate {
    BLACKJACK_RATE(1.5),
    NOT_BLACKJACK_BUT_WIN_RATE(1),
    DRAW_RATE(0),
    LOSE_RATE(-1)
    ;

    private final double rate;

    ProfitRate(final double rate) {
        this.rate = rate;
    }

    public double getRate() {
        return rate;
    }
}
