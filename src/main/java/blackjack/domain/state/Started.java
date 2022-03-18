package blackjack.domain.state;

import blackjack.domain.card.Cards;

public abstract class Started implements State {
    private final Cards cards;

    Started(Cards cards) {
        this.cards = cards;
    }

    public Cards getCards() {
        return cards;
    }
}
