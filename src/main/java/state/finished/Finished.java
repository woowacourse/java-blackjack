package state.finished;

import card.Card;
import card.Cards;
import state.State;
import state.started.Started;

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
    public double profit(final int bettingMoney) {
        return earningRate() * bettingMoney;
    }

    public abstract double earningRate();
}
