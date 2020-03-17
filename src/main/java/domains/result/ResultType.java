package domains.result;

public enum ResultType {
    WIN("승"),
    LOSE("패"),
    DRAW("무"),
    BLACKJACK("블랙잭");

    private String resultType;

    ResultType(String resultType) {
        this.resultType = resultType;
    }

    public String getResultType() {
        return resultType;
    }

    public ResultType oppose() {
        if (this == WIN || this == BLACKJACK) {
            return LOSE;
        }
        if (this == LOSE) {
            return WIN;
        }
        return DRAW;
    }
}
