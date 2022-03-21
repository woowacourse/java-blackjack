package blackjack.model.state.Finished;

import static blackjack.model.BettingRate.LOSE;
import static blackjack.model.BettingRate.WIN;

import blackjack.model.BettingRate;
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
    public BettingRate calculateBettingRate(State otherState) {
        if (otherState.isBlackjack()) {
            return LOSE;
        }
        if (otherState.isBust()) {
            return WIN;
        }
        return BettingRate.compareTo(this, otherState);
    }
}
