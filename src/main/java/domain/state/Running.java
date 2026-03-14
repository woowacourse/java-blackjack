package domain.state;

import domain.member.Hand;

public abstract class Running extends Started {

    public Running(Hand hand) {
        super(hand);
    }

    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public double earningRate() {
        return 0;
    }
}
