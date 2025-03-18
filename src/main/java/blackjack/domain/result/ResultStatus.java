package blackjack.domain.result;

public enum ResultStatus {

    WIN(1.0),
    LOSE(-1.0),
    BLACKJACK(1.5),
    PUSH(0);

    private final double profitRate;

    ResultStatus(final double profitRate) {
        this.profitRate = profitRate;
    }

    public ResultStatus makeOppositeResult() {
        if (this == WIN) {
            return LOSE;
        }
        if (this == LOSE) {
            return WIN;
        }
        return PUSH;
    }

    public int calculateProfit(final int bettingAmount) {
        return (int) (profitRate * bettingAmount);
    }
}
