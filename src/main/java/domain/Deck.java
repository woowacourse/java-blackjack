package domain;

import java.util.List;

public class Deck {

    private final List<Card> cards;

    public Deck(final List<Card> cards) {
        this.cards = List.copyOf(cards);
    }
}
