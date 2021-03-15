package blackjack.domain.state;

import blackjack.domain.card.Cards;

public abstract class Started implements State {
    protected final Cards cards;

    public Started(final Cards cards) {
        this.cards = cards;
    }

    @Override
    public Cards getCards() {
        return this.cards;
    }
}
