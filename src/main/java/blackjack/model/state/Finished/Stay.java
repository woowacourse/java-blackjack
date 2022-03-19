package blackjack.model.state.Finished;

import static blackjack.model.Profits.LOSE;
import static blackjack.model.Profits.WIN;

import blackjack.model.Profits;
import blackjack.model.card.Cards;
import blackjack.model.state.State;

public class Stay extends Finished {

    public Stay(Cards cards) {
        super(cards);
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
    public Profits calculateProfit(State otherState) {
        if (otherState.isBlackjack()) {
            return LOSE;
        }
        if (otherState.isBust()) {
            return WIN;
        }
        return Profits.compareTo(this, otherState);
    }
}
