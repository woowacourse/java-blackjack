package domain.result;

import domain.Money;

public enum PlayerGameOutcome {

    BLACK_JACK_WIN(1.5),
    WIN(1),
    DRAW(0),
    LOSE(-1),
    ;

    private final double profitRate;

    PlayerGameOutcome(double profitRate) {
        this.profitRate = profitRate;
    }

    public Money calculateProfit(Money money) {
        long rounded = Math.round(money.amount() * profitRate);
        return Money.of(rounded);
    }
}
