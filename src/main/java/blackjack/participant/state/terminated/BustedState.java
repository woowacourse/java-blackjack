package blackjack.participant.state.terminated;

import blackjack.participant.Hand;

public class BustedState extends TerminatedState {

    public BustedState(Hand hand) {
        super(hand);
    }
}
