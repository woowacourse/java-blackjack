package blackjack.domain.result;

public enum ResultType {
    BLACKJACK(1.5),
    WIN(1),
    LOSE(-1),
    DRAW(0);

    private final double profitRate;

    ResultType(double profitRate) {
        this.profitRate = profitRate;
    }

    public ResultType reverse() {
        if (this == BLACKJACK) {
            return LOSE;
        }
        if (this == WIN) {
            return LOSE;
        }
        if (this == LOSE) {
            return WIN;
        }
        return this;
    }

    public double computeProfit(double bettingMoney) {
        return this.profitRate * bettingMoney;
    }
}
