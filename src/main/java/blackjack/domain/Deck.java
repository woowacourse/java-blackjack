package blackjack.domain;

import static blackjack.domain.Guest.GUEST_LIMIT_POINT;

import java.util.LinkedHashSet;
import java.util.Set;

public class Deck {

    private static final int BONUS_ACE_POINT = 10;
    private final Set<Card> cards = new LinkedHashSet<>();

    public void addCard(Card card) {
        cards.add(card);
    }

    public int sumPoints() {
        int sumWithoutAce = cards.stream()
                .filter(this::excludeAce)
                .mapToInt(this::getCardPoint)
                .sum();

        int aceCount = countAces();
        if (aceCount == 0) {
            return sumWithoutAce;
        }
        return calculateAcePoint(sumWithoutAce, aceCount);
    }

    private int countAces() {
        return (int) cards.stream()
                .filter(card -> !excludeAce(card))
                .count();
    }

    private int calculateAcePoint(int sumWithoutAce, int aceCount) {
        if (sumWithoutAce + aceCount + BONUS_ACE_POINT <= GUEST_LIMIT_POINT) {
            return sumWithoutAce + aceCount + BONUS_ACE_POINT;
        }
        return sumWithoutAce + aceCount;
    }

    private boolean excludeAce(Card card) {
        return !card.getRank().equals(Rank.ACE);
    }

    private int getCardPoint(Card card) {
        return card.getRank().getPoint();
    }

    public Set<Card> getCards() {
        return cards;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Deck deck = (Deck) o;

        return cards != null ? cards.equals(deck.cards) : deck.cards == null;
    }

    @Override
    public int hashCode() {
        return cards != null ? cards.hashCode() : 0;
    }
}
