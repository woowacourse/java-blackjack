package blackjack.participant.state;

import blackjack.participant.Hand;

public class StandState extends TerminatedState {

    public StandState(Hand hand) {
        super(hand);
    }
}
