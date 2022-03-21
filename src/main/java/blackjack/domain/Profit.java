package blackjack.domain;

public enum Profit {

    BLACKJACK_WIN(1.5),
    WIN(1),
    DRAW(0),
    LOSE(-1),
    ;

    private final double profit;

    Profit(double profit) {
        this.profit = profit;
    }

    public BettingMoney calculateProfit(final BettingMoney money) {
        return money.multiplyProfit(this.profit);
    }
}
