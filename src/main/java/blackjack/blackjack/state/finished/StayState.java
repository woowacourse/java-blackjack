package blackjack.blackjack.state.finished;

import blackjack.blackjack.card.Hand;
import blackjack.blackjack.state.StateType;

public class StayState extends FinishedState {

    public StayState(final Hand hand) {
        super(hand, StateType.STAY);
    }
}
