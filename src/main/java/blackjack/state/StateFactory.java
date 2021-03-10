package blackjack.state;

import blackjack.domain.card.Card;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StateFactory {

    public static State draw(Card firstCard, Card secondCard) {
        Cards cards = new Cards(Arrays.asList(firstCard, secondCard));

        if(cards.getCards().stream().anyMatch(Card::isAce) &&
                cards.getCards().stream().anyMatch(Card::isTen)) {
            return new BlackJack(cards);
        }

        return new Hit(cards);
    }
}
