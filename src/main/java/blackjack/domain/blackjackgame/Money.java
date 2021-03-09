package blackjack.domain.blackjackgame;

import blackjack.domain.player.GameResult;

public class Money {

    private double value;

    public Money(double value) {
        this.value = value;
    }

    public Money add(Money money) {
        return new Money(value + money.getValue());
    }

    public double getValue() {
        return value;
    }

    public Money profit(GameResult gameResult, boolean isBlackjack) {
        if (GameResult.WIN.equals(gameResult) && isBlackjack) {
            return new Money(value * 1.5);
        }
        if (GameResult.WIN.equals(gameResult)) {
            return this;
        }
        if (GameResult.LOSE.equals(gameResult)) {
            return new Money(value * -1);
        }
        return new Money(0);
    }

}
