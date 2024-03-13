package blackjack.domain.bet;

public enum BetLeverage {
    LUCKY(1.5),
    WIN(1),
    LOSE(-1),
    DRAW(0);

    private final double leverage;

    BetLeverage(final double leverage) {
        this.leverage = leverage;
    }

    public BetRevenue applyLeverage(final BetAmount batAmount) {
        return new BetRevenue(batAmount.multiply(leverage));
    }

}
