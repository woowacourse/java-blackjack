package domain.state.type;

import domain.gamer.Hand;
import domain.state.FinishedState;

public class BustState extends FinishedState {

    public BustState(final Hand hand) {
        super(hand);
    }
}
