package domain.card;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;

public class Deck {
    public static final int DECK_COUNT = 6;
    private final Deque<Card> cards;

    private Deck(Deque<Card> cards) {
        this.cards = cards;
    }

    public static Deck combine(final List<Card> cards) {
        return new Deck(new ArrayDeque<>(cards));
    }

    public Card draw() {
        return cards.removeLast();
    }
}
