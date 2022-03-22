package blackjack.domain.state;

import blackjack.domain.Outcome;
import blackjack.domain.bet.BetMoney;
import blackjack.domain.bet.Profit;
import blackjack.domain.card.PlayerCards;

public abstract class Blackjack extends Finished {

    Blackjack(PlayerCards cards) {
        this.cards = cards;
    }

    @Override
    public boolean isBust() {
        return false;
    }

    @Override
    public boolean isBlackjack() {
        return true;
    }

    public abstract Profit profit(Outcome outcome, BetMoney money);
}
