package blackjack.domain.state;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;

public abstract class Finished implements State {

    private final Cards cards;

    protected Finished(final Cards cards) {
        this.cards = cards;
    }

    @Override
    public final State draw(Card card) {
        throw new IllegalStateException();
    }

    @Override
    public final State stay() {
        throw new IllegalStateException();
    }

    @Override
    public Cards cards() {
        return cards;
    }

    @Override
    public boolean isFinished() {
        return true;
    }

    @Override
    public double profit(double money, State state) {
        return money * earningRate(state);
    }

    protected abstract double earningRate(State state);
}
