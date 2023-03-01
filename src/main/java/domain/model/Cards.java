package domain.model;

import domain.type.Letter;
import java.util.Set;

public class Cards {

    private final Set<Card> cards;

    public Cards(final Set<Card> cards) {
        this.cards = cards;
    }

    public boolean contains(final Letter letter) {
        return cards.stream().anyMatch(card -> card.isMatch(letter));
    }

    public void add(final Card card) {
        cards.add(card);
    }

    public Set<Card> getCards() {
        return cards;
    }
}
