package blackjack.domain.state;

import blackjack.domain.Hand;

public class BustState extends ClosedState {

    public BustState(Hand hand) {
        super(hand);
    }
}
