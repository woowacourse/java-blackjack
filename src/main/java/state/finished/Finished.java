package state.finished;

import card.Card;
import card.Cards;
import state.started.Started;
import state.State;

public abstract class Finished extends Started {

    public Finished(final Cards cards) {
        super(cards);
    }

    @Override
    public State draw(Card card) {
        throw new IllegalStateException();
    }

    @Override
    public State stand() {
        throw new IllegalStateException();
    }

    @Override
    public boolean isFinished() {
        return true;
    }

    @Override
    public double profit(double earningRate) {
        return earningRate();
    }

    public abstract double earningRate();
}
