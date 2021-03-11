package blackjack.domain.state;

import blackjack.domain.card.Cards;

public abstract class Started implements State {
    protected Cards cards;

    public Started(final Cards cards) {
        this.cards = cards;
    }

    @Override
    public Cards cards() {
        return cards;
    }

    @Override
    public int score() {
        return cards.calculateScore();
    }
}
