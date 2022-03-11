package blackjack.domain.player;

public enum Result {

    WIN("승"),
    LOSE("패");

    private final String value;

    Result(final String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}
