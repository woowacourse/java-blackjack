package blackjack.domain.result;

public enum Result {
    WIN("승"),
    LOSE("패"),
    DRAW("무");

    private final String value;

    Result(final String value) {
        this.value = value;
    }

    public boolean isSame(final Result result) {
        return this == result;
    }

    public String getValue() {
        return value;
    }
}
