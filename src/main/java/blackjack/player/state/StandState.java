package blackjack.player.state;

import blackjack.player.Hand;

public class StandState extends TerminatedState {

    public StandState(Hand hand) {
        super(hand);
    }
}
