package blackjack.model.state.finished;

import blackjack.model.state.Hand;
import blackjack.model.state.State;

public abstract sealed class FinishedState
        implements State
        permits Blackjack, Bust, Stand {

    protected final Hand hand;

    public FinishedState(Hand hand) {
        this.hand = hand;
    }

    public abstract boolean isBlackjack();

    public abstract boolean isBust();

    public abstract double earningsRate();

    @Override
    public boolean isFinished() {
        return true;
    }

    @Override
    public Hand getHand() {
        return hand;
    }
}
