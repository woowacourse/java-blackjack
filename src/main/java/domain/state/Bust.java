package domain.state;

import domain.member.Hand;

public class Bust extends Finished {

    public Bust(Hand hand) {
        super(hand);
    }

    @Override
    public boolean isBust() {
        return true;
    }

    @Override
    public double earningRate(State dealerState) {
        return -1.0;
    }

    @Override
    public State stay() {
        return this;
    }
}
