package blackjack.domain.state;

import blackjack.domain.participant.Hand;

public abstract class Running extends Started {

    protected Running(Hand hand) {
        super(hand);
    }

    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public double profit(double money) {
        throw new UnsupportedOperationException();
    }

}
