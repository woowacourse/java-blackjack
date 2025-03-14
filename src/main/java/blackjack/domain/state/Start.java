package blackjack.domain.state;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;

public abstract class Start implements State {

    protected final Cards cards;

    protected Start(Cards cards) {
        this.cards = cards;
    }

    public static State of(Card card1, Card card2) {
        Cards cards = new Cards(card1, card2);
        if (cards.isBlackjack()) {
            return new Blackjack(cards);
        }
        return new Hit(cards);
    }
}
