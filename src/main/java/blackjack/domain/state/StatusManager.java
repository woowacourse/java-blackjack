package blackjack.domain.state;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;

public class StatusManager {

    private final Cards cards;

    public StatusManager() {
        this.cards = new Cards();
    }

    public Status start(Card card1, Card card2) {
        cards.receiveCard(card1);
        cards.receiveCard(card2);
        if (cards.isBlackjack()) {
            return new Blackjack(cards);
        }
        return new Hit(cards);
    }
}
