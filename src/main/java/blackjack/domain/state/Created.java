package blackjack.domain.state;

import blackjack.domain.card.Cards;

public abstract class Created implements State {

    protected final Cards cards;

    protected Created(final Cards cards) {
        this.cards = cards;
    }

    @Override
    public Cards cards() {
        return cards;
    }

    @Override
    public boolean isSameStateWith(final Class<? extends State> state) {
        return this.getClass().equals(state);
    }
}
