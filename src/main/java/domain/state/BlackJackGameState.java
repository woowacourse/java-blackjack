package domain.state;

import domain.Hand;

public class BlackJackGameState extends FinishedGameState {
    public BlackJackGameState(Hand hand) {
        super(hand);
    }
}