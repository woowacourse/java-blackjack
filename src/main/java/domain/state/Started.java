package domain.state;

import domain.card.Cards;

public abstract class Started implements State {

    private final Cards cards;

    public Started(Cards cards) {
        this.cards = cards;
    }

    public Cards cards() {
        return cards;
    }
}
