package domain.state;

import domain.Hand;

public class BustGameState extends FinishedGameState {
    public BustGameState(Hand hand) {
        super(hand);
    }
}
