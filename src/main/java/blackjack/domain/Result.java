package blackjack.domain;

public enum Result {

    WIN("승"),
    LOSE("패"),
    DRAW("무");

    private final String result;

    Result(final String result) {
        this.result = result;
    }

    public Result reverse() {
        if (this == WIN) {
            return LOSE;
        }
        if (this == LOSE) {
            return WIN;
        }
        return DRAW;
    }

    public String getResult() {
        return this.result;
    }
}
