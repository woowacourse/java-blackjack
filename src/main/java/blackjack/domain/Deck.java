package blackjack.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {

    private final List<Card> cards;

    public Deck() {
        List<Card> cards = new ArrayList<>();
        for (CardShape cardShape : CardShape.values()) {
            matching(cards, cardShape);
        }

        this.cards = cards;
    }

    private static void matching(List<Card> cards, CardShape cardShape) {
        for (CardValue value : CardValue.values()) {
            Card card = new Card(value, cardShape);
            cards.add(card);
        }
    }

    public void shuffle() {
        Collections.shuffle(cards);
    }

    public Card bringTopCard() {
        return cards.removeFirst();
    }

}
