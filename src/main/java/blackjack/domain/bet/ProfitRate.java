package blackjack.domain.bet;

public enum ProfitRate {
    WIN(1.0),
    DRAW(0.0),
    LOSE(-1.0),
    WIN_BLACKJACK(1.5),
    ;

    private final double rate;

    ProfitRate(double rate) {
        this.rate = rate;
    }

    public int getProfit(int bettingAmount) {
        return (int) Math.round(rate * bettingAmount);
    }
}
