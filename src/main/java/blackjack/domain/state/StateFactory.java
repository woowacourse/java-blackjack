package blackjack.domain.state;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;

public class StateFactory {

    public static State draw(Card firstCard, Card secondCard) {
        Cards cards = new Cards(firstCard, secondCard);
        if (cards.isBlackJack()) {
            return new BlackJack(cards);
        }
        return new Hit(cards);
    }
}
