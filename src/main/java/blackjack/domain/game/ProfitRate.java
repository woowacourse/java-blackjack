package blackjack.domain.game;

public enum ProfitRate {

    BLACKJACK_WITH_INITIAL_HAND(1.5),
    WIN(1),
    LOSE(-1),
    DRAW(0);

    private final double rate;

    ProfitRate(double rate) {
        this.rate = rate;
    }

    public int calculateProfit(int amount) {
        return (int) (amount * this.rate);
    }

    public double getRate() {
        return rate;
    }
}
