package domain;

public enum MatchResult {
    BLACKJACK_WIN(1.5),
    WIN(1.0),
    DRAW(0.0),
    LOSE(-1.0);

    private final double profitRate;

    MatchResult(double profitRate) {
        this.profitRate = profitRate;
    }

    public double profitRate() {
        return profitRate;
    }
}
