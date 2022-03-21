package blackjack.model.state.finished;

import static blackjack.model.betting.BettingRate.LOSE;
import static blackjack.model.betting.BettingRate.WIN;

import blackjack.model.betting.BettingRate;
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
