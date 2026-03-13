package blackjack.domain.deck;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardShape;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {

    private static final List<Card> cards = new ArrayList<>();

    static {
        for (CardShape cardShape : CardShape.values()) {
            cards.addAll(Card.makeCardsWith(cardShape));
        }
    }

    private Deck() {
    }

    public static void shuffle() {
        Collections.shuffle(cards);
    }

    public static Card pop() {
        return cards.removeFirst();
    }

}
