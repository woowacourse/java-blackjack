package blackjack.domain.participant;

public enum Result {
    WIN("승"),
    PUSH("무"),
    LOSE("패"),
    EMPTY("");

    private final String value;

    Result(final String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}
