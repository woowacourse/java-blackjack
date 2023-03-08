package domain.model;

import domain.type.Denomination;
import java.util.Set;

public class Cards {

    private final Set<Card> cards;

    public Cards(final Set<Card> cards) {
        this.cards = cards;
    }

    public int count(final Denomination denomination) {
        return (int) cards.stream()
            .filter(card -> card.isMatch(denomination))
            .count();
    }

    public void add(final Card card) {
        cards.add(card);
    }

    public Set<Card> getCards() {
        return cards;
    }
}
