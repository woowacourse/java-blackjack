package domain;

public enum MatchResult {
    BLACKJACK("블랙잭", 1.5),
    WIN("승", 1.0),
    LOSE("패", -1.0),
    DRAW("무", 0.0);

    private final String korean;
    private final double multiplier;

    MatchResult(String korean, double multiplier) {
        this.korean = korean;
        this.multiplier = multiplier;
    }

    public double getMultiplier() {
        return multiplier;
    }
}
