package blackjack.model.state.Finished;

import static blackjack.model.Profits.DRAW;
import static blackjack.model.Profits.WIN;

import blackjack.model.Profits;
import blackjack.model.card.Cards;
import blackjack.model.state.State;

public class Blackjack extends Finished {

    public Blackjack(Cards cards) {
        super(cards);
    }

    @Override
    public boolean isBust() {
        return false;
    }

    @Override
    public boolean isBlackjack() {
        return true;
    }

    @Override
    public Profits calculateProfit(State otherState) {
        if (otherState.isBlackjack()) {
            return DRAW;
        }
        return WIN;
    }
}
