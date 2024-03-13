package domain.constants;

public enum ProfitRate {
    BLACKJACK(1.5),
    WIN(1.0),
    PUSH(0),
    LOSE(-1.0);

    private final double rate;

    ProfitRate(final double rate) {
        this.rate = rate;
    }

    public int getProfit(final int betAmount) {
        return (int) (rate * betAmount);
    }
}
