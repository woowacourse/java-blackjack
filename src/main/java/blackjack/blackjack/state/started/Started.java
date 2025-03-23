package blackjack.blackjack.state.started;

import blackjack.blackjack.card.Hand;
import blackjack.blackjack.state.State;
import blackjack.blackjack.state.StateType;

public abstract class Started implements State {

    protected final Hand hand;
    protected final StateType stateType;

    public Started(final Hand hand, final StateType stateType) {
        this.hand = hand;
        this.stateType = stateType;
    }

    @Override
    public int calculateScore() {
        return hand.calculateScore();
    }

    @Override
    public Hand cards() {
        return hand;
    }

    @Override
    public StateType getStateType() {
        return stateType;
    }
}
