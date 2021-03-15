package blackjack.domain.result;

public enum Result {
    BLACKJACK(1.5),
    WIN(1.0),
    LOSE(-1.0),
    DRAW(0);

    private final double profit;

    Result(final double profit) {
        this.profit = profit;
    }

    public int calculateProfit(final int money) {
        return (int) (money * profit);
    }
}
