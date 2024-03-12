package blackjack.player.state;

import blackjack.player.Hand;

public class BustedState extends TerminatedState {

    public BustedState(Hand hand) {
        super(hand);
    }
}
