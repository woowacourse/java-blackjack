package domain.state.type;

import domain.gamer.Hand;
import domain.state.FinishedState;

public class StayState extends FinishedState {

    public StayState(final Hand hand) {
        super(hand);
    }

    @Override
    public StateType type() {
        return StateType.STAY_STATE;
    }
}
