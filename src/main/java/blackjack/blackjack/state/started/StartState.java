package blackjack.blackjack.state.started;

import blackjack.blackjack.card.Hand;
import blackjack.blackjack.state.State;
import blackjack.blackjack.state.StateType;

public abstract class StartState implements State {

    protected final Hand hand;
    protected final StateType stateType;

    public StartState(final Hand hand, final StateType stateType) {
        this.hand = hand;
        this.stateType = stateType;
    }

    @Override
    public final int calculateScore() {
        return hand.calculateScore();
    }

    @Override
    public final Hand cards() {
        return hand;
    }

    @Override
    public final StateType getStateType() {
        return stateType;
    }
}
