package domain;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Deck {

    private final Queue<Card> cards;

    public Deck(final List<Card> cards) {
        this.cards = new LinkedList<>(cards);
    }
}
