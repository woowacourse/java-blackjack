package blackjack.domain.state.finished;

import blackjack.domain.state.hand.Hand;

public class Blackjack extends Finished {

    public Blackjack(final Hand hand) {
        super(hand);
    }

    public double earningRate() {
        return 1.5d;
    }

    @Override
    public boolean isBlackJack() {
        return true;
    }
}
