package blackjack.domain.state;

import blackjack.domain.card.Cards;

public abstract class Started implements State {

    protected final Cards cards;

    public Started(Cards cards) {
        this.cards = Cards.of(cards.getCards());
    }

    @Override
    public Cards cards() {
        return cards;
    }

    @Override
    public int calculateScore() {
        return cards.calculateScore();
    }
}
