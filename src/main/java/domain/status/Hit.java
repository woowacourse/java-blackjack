package domain.status;

import domain.card.Card;
import domain.card.Cards;

public class Hit implements Status {

    private final Cards cards;

    public Hit(final Cards cards) {
        this.cards = cards;
    }

    public Status draw(final Card card) {
        Cards newCards = cards.receiveCard(card);
        if (newCards.isBust()) {
            return new Bust();
        }
        return new Hit(newCards);
    }

    public Status selectStand() {
        return new Stand(cards);
    }
}
