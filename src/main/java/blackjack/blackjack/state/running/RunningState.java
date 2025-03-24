package blackjack.blackjack.state.running;

import blackjack.blackjack.card.Hand;
import blackjack.blackjack.state.State;
import blackjack.blackjack.state.StateType;
import blackjack.blackjack.state.finished.StayState;
import blackjack.blackjack.state.started.StartState;

public abstract class RunningState extends StartState {

    public RunningState(final Hand hand) {
        super(hand, StateType.RUNNING);
    }

    @Override
    public final State stay() {
        return new StayState(hand);
    }

    @Override
    public final boolean isNotFinished() {
        return true;
    }
}
