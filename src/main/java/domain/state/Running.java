package domain.state;

import domain.member.Hand;

public abstract class Running extends Started {
    protected static final int BUST_CONDITION = 22;
    protected static final int BLACKJACK_CONDITION = 21;

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
