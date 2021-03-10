package blackjack.domain.state;

import blackjack.domain.participant.Hand;

public abstract class Finished implements State{

    @Override
    public boolean isFinished() {
        return true;
    }

    @Override
    public double profit(double money) {
        return money * earningRate();
    }

    @Override
    public State stay() {
        throw new UnsupportedOperationException();
    }

    @Override
    public State check(Hand hand) {
        throw new UnsupportedOperationException();
    }

    public abstract double earningRate();
}
