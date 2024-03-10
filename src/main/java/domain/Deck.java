package domain;

import domain.card.Card;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;

public class Deck {
    private final Deque<Card> cards;

    private Deck(Deque<Card> cards) {
        this.cards = cards;
    }

    public static Deck from(final List<Card> cards) {
        return new Deck(new ArrayDeque<>(cards));
    }

    public Card draw() {
        return cards.removeLast();
    }
}
