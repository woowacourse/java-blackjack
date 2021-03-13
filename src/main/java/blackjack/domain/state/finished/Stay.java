package blackjack.domain.state.finished;

import blackjack.domain.state.hand.Hand;

public class Stay extends Finished {

    public Stay(final Hand hand) {
        super(hand);
    }

    @Override
    public double earningRate() {
        return 1.0d;
    }
}
