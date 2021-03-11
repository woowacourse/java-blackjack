package blackjack.domain.state;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;

public class StateFactory {
    public static State createInitialState(final Card firstCard, final Card secondCard) {
        final Cards cards = new Cards(firstCard, secondCard);
        if (cards.isBlackjack()) {
            return new Blackjack(cards);
        }
        return new Hit(cards);
    }
}
