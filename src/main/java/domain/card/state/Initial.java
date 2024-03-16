package domain.card.state;

import domain.card.Cards;

public abstract class Initial implements CardState {
    private final Cards cards;

    protected Initial(Cards cards) {
        this.cards = cards;
    }

    @Override
    public Cards cards() {
        return this.cards;
    }
}
