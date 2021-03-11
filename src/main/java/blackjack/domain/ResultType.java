package blackjack.domain;

public enum ResultType {
    WIN("승"),
    DRAW("무"),
    LOSE("패");

    private final String name;

    ResultType(final String name) {
        this.name = name;
    }

    public ResultType switchPosition() {
        if (this == WIN) {
            return LOSE;
        }
        if (this == LOSE) {
            return WIN;
        }
        return DRAW;
    }

    public String getName() {
        return name;
    }
}
