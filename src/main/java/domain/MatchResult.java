package domain;

public enum MatchResult {
    BLACKJACK_WIN(1.5),
    WIN(1.0),
    DRAW(0.0),
    LOSE(-1.0),
    ;

    private final double profitRate;

    MatchResult(double profitRate) {
        this.profitRate = profitRate;
    }

    public MatchResult opposite() {
        if (this == BLACKJACK_WIN || this == WIN) {
            return LOSE;
        }
        if (this == LOSE) {
            return WIN;
        }
        return DRAW;
    }

    public double getProfitRate() {
        return profitRate;
    }
}
