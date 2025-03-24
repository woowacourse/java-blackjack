package domain.state.type;

import domain.gamer.Hand;
import domain.state.FinishedState;

public class BustState extends FinishedState {

    public BustState(final Hand hand) {
        super(hand);
    }

    @Override
    public StateType type() {
        return StateType.BUST_STATE;
    }
}
