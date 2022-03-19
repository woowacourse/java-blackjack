package blackjack.domain.state;

import blackjack.domain.card.HoldCards;
import blackjack.domain.entry.BettingMoney;

public abstract class Running implements State {
    private final HoldCards holdCards;

    Running(HoldCards holdCards) {
        this.holdCards = holdCards;
    }

    public HoldCards holdCards() {
        return holdCards;
    }

    @Override
    public final State isBlackjack() {
        if (holdCards().isBlackjack()) {
            return new Blackjack(holdCards());
        }
        return new Hit(holdCards());
    }

    @Override
    public final boolean isFinished() {
        return false;
    }

    @Override
    public double profit(BettingMoney bettingMoney) {
        throw new IllegalStateException();
    }

    @Override
    public HoldCards getHoldCards() {
        return holdCards;
    }
}
