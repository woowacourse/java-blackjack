package BlackJack.domain;

public enum Result {
    BLACKJACK("승"),
    WIN("승"),
    DRAW("무"),
    LOSE("패");

    private final String value;

    Result(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
