package blackjack.domain;

import java.util.HashSet;
import java.util.Set;

public class Deck {

    private final Set<Card> cards = new HashSet<>();

    public int sumPoints() {
        return cards.stream()
                .mapToInt(this::getCardPoint)
                .sum();
    }

    private int getCardPoint(Card card) {
        return card.getRank().getPoint();
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Deck deck = (Deck) o;

        return cards != null ? cards.equals(deck.cards) : deck.cards == null;
    }

    @Override
    public int hashCode() {
        return cards != null ? cards.hashCode() : 0;
    }
}
