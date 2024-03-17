package domain.card;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;

public class Deck {
    private final Deque<Card> cards;

    public Deck(List<Card> cards) {
        this.cards = new ArrayDeque<>(cards);
    }

    public Card draw() {
        return cards.removeLast();
    }
}
