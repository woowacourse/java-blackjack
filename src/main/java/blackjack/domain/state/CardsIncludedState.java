package blackjack.domain.state;

import blackjack.domain.cards.Cards;

public abstract class CardsIncludedState implements State {
    protected final Cards cards;

    public CardsIncludedState(final Cards cards) {
        this.cards = cards;
    }

    @Override
    public Cards cards() {
        return cards;
    }
}
