package state.running;

import card.CardHand;
import result.GameResult;
import state.Started;
import state.State;
import state.StateType;

public abstract class Running extends Started {
    public Running(final CardHand cardHand) {
        super(cardHand);
    }

    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public GameResult calculatePlayerResult(State dealerState) {
        throw new IllegalStateException();
    }

    @Override
    public StateType type() {
        return StateType.RUNNING;
    }
}
