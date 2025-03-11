package domain.blackJack;

public enum MatchResult {
    WIN("승", 2),
    BLACKJACK("블랙잭", 1.5),
    DRAW("무", 1),
    LOSE("패", 0);

    private final String value;
    private final double rate;

    MatchResult(String value, double rate) {
        this.value = value;
        this.rate = rate;
    }

    public String getValue() {
        return value;
    }

    public double getRate() {
        return rate;
    }
}