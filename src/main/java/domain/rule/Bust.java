package domain.rule;

import domain.card.Hand;

public class Bust extends Finished {
    public Bust(Hand hand) {
        super(hand);
    }

    @Override
    public double earningRate(State dealerState) {
        return -1.0;
    }

    @Override
    public boolean isBust() {
        return true;
    }
}
