package blackjack.domain.state;

import blackjack.domain.participant.Hand;

public class BustState extends ClosedState {

    BustState(Hand hand) {
        super(hand);
    }

    @Override
    public double getProfitRate(Hand other) {
        return -1;
    }
}
