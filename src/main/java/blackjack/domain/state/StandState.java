package blackjack.domain.state;

import blackjack.domain.Hand;

public class StandState extends ClosedState {

    public StandState(Hand hand) {
        super(hand);
    }
}
