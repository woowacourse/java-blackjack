package domain.blackjack;

public enum Result {
    WIN("승"),
    DRAW("무"),
    LOSE("패"),
    ;

    private final String value;

    Result(String value) {
        this.value = value;
    }

    public Result convertToOpposite() {
        if (this == WIN) {
            return LOSE;
        }

        if (this == LOSE) {
            return WIN;
        }

        return DRAW;
    }

    public String getValue() {
        return value;
    }
}
