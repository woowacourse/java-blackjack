package blackjack.model.state;

import blackjack.model.card.Card;
import blackjack.model.card.Cards;

public class Hit implements State {

    private final Cards cards;

    protected Hit(final Cards cards) {
        this.cards = cards;
    }

    @Override
    public State add(Card card) {
        Cards cards = this.cards.add(card);

        if (cards.isMaxScore()) {
            return new Stay(cards);
        }
        if (cards.isBust()) {
            return new Bust(cards);
        }
        return new Hit(cards);
    }

    @Override
    public boolean isReady() {
        return false;
    }
}
