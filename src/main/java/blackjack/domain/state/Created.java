package blackjack.domain.state;

import blackjack.domain.card.Cards;

public abstract class Created implements State {

    protected final Cards cards;

    protected Created(final Cards cards) {
        this.cards = cards;
    }

    public Cards cards() {
        return cards;
    }
}
