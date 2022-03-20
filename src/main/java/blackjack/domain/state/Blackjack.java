package blackjack.domain.state;

import blackjack.domain.card.Hand;

public class Blackjack extends Finished {

    protected Blackjack(Hand hand) {
        super(hand);
    }

    @Override
    public double earningRate(State state) {
        if (state.hand().isBlackjack()) {
            return 1;
        }

        return 1.5;
    }

    @Override
    public boolean isRunning() {
        return false;
    }
}
