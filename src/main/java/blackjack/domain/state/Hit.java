package blackjack.domain.state;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;

public class Hit extends Running {

    public Hit(final Cards cards) {
        super(cards);
    }

    public State draw(Card card) {
        cards = cards.addCard(card);
        if (cards.isBust()) {
            return new Bust(cards);
        }
        return new Hit(cards);
    }

    public State stay() {
        return new Stay(cards);
    }
}
