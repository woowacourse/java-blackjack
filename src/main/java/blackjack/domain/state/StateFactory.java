package blackjack.domain.state;

import blackjack.domain.card.Card;
import blackjack.domain.card.Hand;

public class StateFactory {
    public static State draw(Card firstCard, Card secondCard) {
        Hand cards = new Hand(firstCard, secondCard);
        if (cards.isBlackjack()) {
            return new Blackjack(cards);
        }
        return new Hit(cards);
    }
}
