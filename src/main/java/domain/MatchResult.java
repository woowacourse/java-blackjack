package domain;

public enum MatchResult {
    BLACKJACK(1.5),
    WIN(1.0),
    LOSE(-1.0),
    DRAW(0.0);

    private final double multiplier;

    MatchResult(double multiplier) {
        this.multiplier = multiplier;
    }

    public double getMultiplier() {
        return multiplier;
    }
}
