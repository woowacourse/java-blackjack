package blackjack.domain.result;

public enum Result {

    BLACKJACK(1.5),
    WIN(1),
    DRAW(0),
    LOSE(-1);

    private final double profitRate;

    Result(final double profitRate) {
        this.profitRate = profitRate;
    }

    public Result reverseResult() {
        if (this == WIN) {
            return LOSE;
        }
        if (this == LOSE) {
            return WIN;
        }
        return DRAW;
    }

    public int calculateProfit(final int bettingAmount) {
        return (int) Math.floor(bettingAmount * this.profitRate);
    }
}
