package blackjack.domain.state;

import blackjack.domain.card.Cards;

public abstract class Started implements Status {

    protected final Cards cards;

    protected Started(Cards cards) {
        this.cards = new Cards(cards.getCards());
    }

    @Override
    public final Cards getCards() {
        return cards;
    }
}
