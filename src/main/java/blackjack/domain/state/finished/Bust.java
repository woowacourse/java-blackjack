package blackjack.domain.state.finished;

import blackjack.domain.state.hand.Hand;

public class Bust extends Finished {

    public Bust(final Hand hand) {
        super(hand);
    }

    @Override
    public double earningRate() {
        return -1;
    }

    @Override
    public boolean isBust() {
        return true;
    }
}
