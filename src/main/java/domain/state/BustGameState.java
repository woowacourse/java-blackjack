package domain.state;

import domain.Hand;

public class BustGameState extends EndGameState {
    public BustGameState(Hand hand) {
        super(hand);
    }
}
