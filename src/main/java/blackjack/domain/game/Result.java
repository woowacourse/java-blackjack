package blackjack.domain.game;

import blackjack.domain.card.Hand;
import blackjack.domain.participant.Score;

public enum Result {
    BLACKJACK(1.5),
    WIN(1),
    DRAW(0),
    LOSE(-1);

    private final double yield;

    Result(double yield) {
        this.yield = yield;
    }

    public Money calculateYield(Money money) {
        return money.multiply(this.yield);
    }
}
