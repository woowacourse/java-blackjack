package blackjack.model.state.Finished;

import static blackjack.model.BettingRate.DRAW;
import static blackjack.model.BettingRate.LOSE;

import blackjack.model.BettingRate;
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
    public BettingRate calculateBettingRate(State otherState) {
        if (otherState.isBust()) {
            return DRAW;
        }
        return LOSE;
    }
}
