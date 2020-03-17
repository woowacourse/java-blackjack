package domains.result;

public enum ResultType {
    WIN("승", 1),
    LOSE("패", -1),
    DRAW("무", 0),
    BLACKJACK("블랙잭", 1.5);

    private String resultType;
    private double profitRate;

    ResultType(String resultType, double profitRate) {
        this.resultType = resultType;
        this.profitRate = profitRate;
    }

    public String getResultType() {
        return resultType;
    }

    public double getProfitRate() {
        return profitRate;
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
