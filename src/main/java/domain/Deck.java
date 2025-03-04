package domain;

import java.util.List;

public class Deck {
    private final Cards cards;

    public Deck(Cards cards) {
        this.cards = cards;
    }

    public int size() {
        return cards.size();
    }
}
