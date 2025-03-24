package domain.state;

import domain.gamer.Hand;

public abstract class RunningState extends StartedState {

    public RunningState(final Hand hand) {
        super(hand);
    }

    @Override
    public final boolean isFinished() {
        return false;
    }
}
