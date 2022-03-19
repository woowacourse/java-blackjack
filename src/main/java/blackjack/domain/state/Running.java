package blackjack.domain.state;

import blackjack.domain.Outcome;
import blackjack.domain.bet.BetMoney;
import blackjack.domain.bet.Profit;
import blackjack.domain.card.Card;

public abstract class Running extends Ready {

    Running() {
    }

    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public boolean isBust() {
        return false;
    }

    @Override
    public boolean isBlackjack() {
        return false;
    }

    @Override
    public Profit profit(Outcome outcome, BetMoney money) {
        throw new IllegalStateException();
    }

    public abstract State draw(Card card);

    public abstract State stay();
}
