package blackjack.domain.state;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;

import java.util.Arrays;

public class StateFactory {
    public static State draw(Card firstCard, Card secondCard) {
        Cards cards = new Cards(Arrays.asList(firstCard, secondCard));
        if (cards.isBlackJack()) {
            return new BlackJack();
        }
        return new Hit(Arrays.asList(firstCard, secondCard));
    }
}
