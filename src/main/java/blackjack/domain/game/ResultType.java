package blackjack.domain.game;

public enum ResultType {
    WIN("승"),
    PUSH("무"),
    LOSE("패");

    private final String value;

    ResultType(final String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}
