package blackjack.domain.state;

import blackjack.domain.card.Card;

public abstract class Started implements State {

    final Cards cards;

    Started(Cards cards) {
        this.cards = cards;
    }

    @Override
    public abstract State draw(Card card);

    @Override
    public final Cards getCards() {
        return cards;
    }

    @Override
    public abstract State stay();

    @Override
    public boolean isDrawable() {
        return true;
    }
}
