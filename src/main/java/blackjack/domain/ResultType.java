package blackjack.domain;

public enum ResultType {
    WIN("승"),
    LOSE("패"),
    PUSH("무");

    private final String type;

    ResultType(final String type) {
        this.type = type;
    }

    public static ResultType getReverseType(ResultType type) {
        if (type == WIN) {
            return LOSE;
        }
        if (type == LOSE) {
            return WIN;
        }
        return PUSH;
    }

    public String getType() {
        return type;
    }
}
