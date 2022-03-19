package blackjack.model.state.Finished;

import static blackjack.model.Profits.DRAW;
import static blackjack.model.Profits.LOSE;

import blackjack.model.Profits;
import blackjack.model.card.Cards;
import blackjack.model.state.State;

public class Bust extends Finished {

    public Bust(final Cards cards) {
        super(cards);
    }

    @Override
    public boolean isBust() {
        return true;
    }

    @Override
    public boolean isBlackjack() {
        return false;
    }

    @Override
    public Profits calculateProfit(State otherState) {
        if (otherState.isBust()) {
            return DRAW;
        }
        return LOSE;
    }
}
