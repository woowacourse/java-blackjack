package domain.model;

import java.util.Set;

public class Cards {

    private final Set<Card> cards;

    public Cards(final Set<Card> cards) {
        this.cards = cards;
    }

    public void add(final Card card) {
        cards.add(card);
    }

    public boolean contains(final Card card) {
        return cards.contains(card);
    }
}
