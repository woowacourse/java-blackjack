package blackjack.model.state.finished;

import static blackjack.model.BettingRate.BLACKJACK;
import static blackjack.model.BettingRate.DRAW;

import blackjack.model.BettingRate;
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
    public BettingRate calculateBettingRate(State otherState) {
        if (otherState.isBlackjack()) {
            return DRAW;
        }
        return BLACKJACK;
    }
}
