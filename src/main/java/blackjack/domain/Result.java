package blackjack.domain;

public enum Result {
    WIN("승"),
    DRAW("무"),
    LOSE("패");

    private final String state;

    Result(final String state) {
        this.state = state;
    }

    public String getState() {
        return state;
    }
}
