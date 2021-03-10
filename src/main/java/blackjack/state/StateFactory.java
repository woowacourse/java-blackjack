package blackjack.state;

import blackjack.domain.card.Card;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StateFactory {

    public static State draw(Card firstCard, Card secondCard) {
        List<Card> cards = new ArrayList<>(Arrays.asList(firstCard, secondCard));

        if(cards.stream().anyMatch(Card::isAce) && cards.stream().anyMatch(Card::isTen)) {
            return new BlackJack(cards);
        }

        return new Hit(cards);
    }
}
