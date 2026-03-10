package domain;

import java.util.Collections;
import java.util.List;

public class Deck {
    private final List<Card> cards;

    public Deck(List<Card> cards) {
        this.cards = cards;
        shuffle();
    }

    public Card draw() {
        return cards.removeFirst();
    }

    private void shuffle() {
        Collections.shuffle(cards);
    }
}
