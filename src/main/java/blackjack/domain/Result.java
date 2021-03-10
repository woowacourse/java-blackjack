package blackjack.domain;

public enum Result {
    BLACKJACK("블랙잭"),
    WIN("승"),
    DRAW("무"),
    LOSE("패");

    private final String value;

    Result(final String value) {
        this.value = value;
    }

    public boolean isSameResult(final Result result) {
        return this == result;
    }

    public String getValue() {
        return value;
    }
}
