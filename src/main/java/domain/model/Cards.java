package domain.model;

import domain.type.Denomination;

import java.util.List;

public class Cards {

    private final List<Card> cards;

    public Cards(final List<Card> cards) {
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

    public List<Card> getCards() {
        return cards;
    }
}
