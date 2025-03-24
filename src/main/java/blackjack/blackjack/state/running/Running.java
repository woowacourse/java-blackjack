package blackjack.blackjack.state.running;

import blackjack.blackjack.card.Hand;
import blackjack.blackjack.state.State;
import blackjack.blackjack.state.StateType;
import blackjack.blackjack.state.finished.Stay;
import blackjack.blackjack.state.started.Started;

public abstract class Running extends Started {

    public Running(final Hand hand) {
        super(hand, StateType.RUNNING);
    }

    @Override
    public State stay() {
        return new Stay(hand);
    }

    @Override
    public boolean isNotFinished() {
        return true;
    }
}
