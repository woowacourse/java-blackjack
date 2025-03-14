package state.running;

import card.Cards;
import state.started.Started;

public abstract class Running extends Started {

    public Running(final Cards cards) {
        super(cards);
    }

    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public double profit(double earningRate) {
        return 0;
    }
}
