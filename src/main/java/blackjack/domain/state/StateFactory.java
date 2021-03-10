package blackjack.domain.state;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;

public class StateFactory {
    private StateFactory() {
    }

    public static State createState(Card card1, Card card2) {
        Cards cards = new Cards(card1, card2);
        if (cards.isBlackjack()) {
            return new Blackjack(cards);
        }
        return new Hit(cards);
    }
}
