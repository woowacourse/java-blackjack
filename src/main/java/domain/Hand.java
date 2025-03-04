package domain;

import java.util.List;

public class Hand {

    private final List<Card> cards;

    public Hand(final List<Card> cards) {
        this.cards = cards;
    }

    public void add(final Card card) {
        cards.add(card);
    }
}
