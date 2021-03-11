package blackjack.domain.state;

import blackjack.domain.card.Card;
import blackjack.domain.card.PlayerCards;

public class StateFactory {
    public static State draw(Card firstCard, Card secondCard) {
        PlayerCards cards = new PlayerCards(firstCard, secondCard);
        if (cards.isBlackjack()) {
            return new Blackjack(cards);
        }
        return new Hit(cards);
    }
}
