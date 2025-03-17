package domain.state;

import domain.gamer.Hand;

public abstract class Running extends Started {

    public Running(final Hand hand) {
        super(hand);
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}
