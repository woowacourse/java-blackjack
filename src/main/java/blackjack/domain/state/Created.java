package blackjack.domain.state;

import blackjack.domain.card.Cards;

public abstract class Created implements State {

    final Cards cards;

    Created(final Cards cards) {
        this.cards = cards;
    }

    public Cards cards() {
        return cards;
    }
}
