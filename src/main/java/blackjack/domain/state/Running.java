package blackjack.domain.state;

import blackjack.domain.card.Cards;

public abstract class Running extends Started {

    protected Running(Cards cards) {
        super(cards);
    }

    @Override
    public final boolean isRunning() {
        return true;
    }

    @Override
    public final boolean isFinished() {
        return false;
    }

    @Override
    public final double earningRate(State state) {
        throw new IllegalStateException();
    }
}
