package blackjack.domain.state;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;

public class Hit implements State {
    private final Cards cards;

    public Hit(final Cards cards) {
        this.cards = cards;
    }

    public State draw(final Card card) {
        cards.add(card);
        if (cards.isBust()) {
            return new Bust(cards);
        }
        return new Hit(cards);
    }
}
