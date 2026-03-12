package domain.state;

import domain.Hand;

public class BlackJackGameState extends EndGameState {
    public BlackJackGameState(Hand hand) {
        super(hand);
    }
}