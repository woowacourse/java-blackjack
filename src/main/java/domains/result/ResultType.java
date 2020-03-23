package domains.result;

public enum ResultType {
    WIN(1),
    LOSE(-1),
    DRAW(0),
    BLACKJACK(1.5);

    private double profitRate;

    ResultType(double profitRate) {
        this.profitRate = profitRate;
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

    public double getProfitRate() {
        return profitRate;
    }
}
