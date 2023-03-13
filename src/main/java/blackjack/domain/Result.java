package blackjack.domain;

public enum Result {
    WIN("승", 1),
    BLACKJACK("승", 1.5),
    PUSH("무", 1),
    LOSE("패", -1);

    private final String value;
    private final double rate;

    Result(final String value, final double rate) {
        this.value = value;
        this.rate = rate;
    }

    public String getValue() {
        return this.value;
    }

    public double getRate() {
        return this.rate;
    }
}
