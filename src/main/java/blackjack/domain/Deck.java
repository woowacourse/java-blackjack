package blackjack.domain;

import java.util.LinkedHashSet;
import java.util.Set;

public class Deck {

    private final Set<Card> cards = new LinkedHashSet<>();

    public int sumPoints() {
        int sumWithoutAce = cards.stream()
                .filter(this::excludeAce)
                .mapToInt(this::getCardPoint)
                .sum();

        int aceCount = (int) cards.stream().filter(card -> !excludeAce(card)).count();
        if (aceCount > 0) {
            if (sumWithoutAce + aceCount + 10 <= 21) {
                return sumWithoutAce + aceCount + 10;
            }
        }
        return sumWithoutAce + aceCount;
    }

    private boolean excludeAce(Card card) {
        return !card.getRank().equals(Rank.ACE);
    }

    private int getCardPoint(Card card) {
        return card.getRank().getPoint();
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    public Set<Card> getCards() {
        return cards;
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
