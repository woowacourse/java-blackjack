package blackjack.domain.state;

import blackjack.domain.card.Hand;

public class Bust extends Finished {

    protected Bust(Hand hand) {
        super(hand);
    }

    @Override
    public double earningRate(State state) {
        return -1;
    }

    @Override
    public boolean isRunning() {
        return false;
    }
}
