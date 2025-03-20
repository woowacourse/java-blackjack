package domain.state.type;

import domain.gamer.Hand;
import domain.state.FinishedState;

public class BlackjackState extends FinishedState {

    public BlackjackState(final Hand hand) {
        super(hand);
    }

    @Override
    public StateType type() {
        return StateType.BLACKJACK_STATE;
    }
}
