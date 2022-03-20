package blackjack.domain.state;

import blackjack.domain.cards.Cards;

public abstract class Init implements State {
    protected final Cards cards;

    public Init(final Cards cards) {
        this.cards = cards;
    }

    @Override
    public Cards cards() {
        return cards;
    }
}
