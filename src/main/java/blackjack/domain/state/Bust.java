package blackjack.domain.state;

import blackjack.domain.Outcome;
import blackjack.domain.bet.BetMoney;
import blackjack.domain.bet.Profit;
import blackjack.domain.card.PlayerCards;

public abstract class Bust extends Finished {

    Bust(PlayerCards cards) {
        this.cards = cards;
    }

    @Override
    public boolean isBust() {
        return true;
    }

    @Override
    public boolean isBlackjack() {
        return false;
    }

    public abstract Profit profit(Outcome outcome, BetMoney money);
}
