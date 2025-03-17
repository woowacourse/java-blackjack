package state.running;

import card.CardHand;
import state.Started;

public abstract class Running extends Started {
    public Running(final CardHand cardHand) {
        super(cardHand);
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}
