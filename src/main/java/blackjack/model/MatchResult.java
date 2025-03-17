package blackjack.model;

public enum MatchResult {

    BLACKJACK(2.5),
    WIN(2.0),
    DRAW(1.0),
    LOSE(0.0),
    ;

    private final double multiplier;

    MatchResult(double multiplier) {
        this.multiplier = multiplier;
    }

    public int applyMultiplier(int amount) {
        return (int) (amount * multiplier);
    }
}
