package domain;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class Deck {

    private final Deque<Card> deck;

    private Deck(final Deque<Card> deck) {
        this.deck = deck;
    }

    public static Deck from(CardGenerator cardGenerator) {
        List<Card> cards = cardGenerator.shuffle();
        return new Deck(new ArrayDeque<>(cards));
    }
}
