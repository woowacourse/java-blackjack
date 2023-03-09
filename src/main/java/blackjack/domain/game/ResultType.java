package blackjack.domain.game;

public enum ResultType {
    BLACK_JACK("블랙잭 승"),
    WIN("승"),
    LOSE("패"),
    PUSH("무");

    private final String type;

    ResultType(final String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
