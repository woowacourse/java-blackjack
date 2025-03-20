package state.finished;

import card.Cards;

public class Stand extends Finished {

    public Stand(Cards cards) {
        super(cards);
    }

    @Override
    public double earningRate() {
        return 1;
    }

    @Override
    public boolean isBlackjack() {
        return false;
    }

    @Override
    public boolean isBust() {
        return false;
    }
}
