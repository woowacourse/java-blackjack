package blackjack.domain;

import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;

public class Deck {

    private static final int HAS_NOT_ACE = 0;
    private static final int ACE_ELEVEN = 11;
    private static final int MINUS_ACE_SELF_SIZE = 1;

    private final Set<Card> deck = new LinkedHashSet<>();

    public void addCard(Card card) {
        deck.add(card);
    }

    public int sumPoints() {
        int sumWithoutAce = deck.stream()
                .filter(card -> !isAce(card))
                .mapToInt(this::getCardPoint)
                .sum();

        int aceCount = countAces();
        if (aceCount == HAS_NOT_ACE) {
            return sumWithoutAce;
        }
        return calculateAcePoint(sumWithoutAce, aceCount);
    }

    private int countAces() {
        return (int) deck.stream()
                .filter(this::isAce)
                .count();
    }

    private int calculateAcePoint(int sumWithoutAce, int aceCount) {
        if (sumWithoutAce + aceCount + (ACE_ELEVEN - MINUS_ACE_SELF_SIZE) <= Match.MAX_WINNER_POINT) {
            return sumWithoutAce + aceCount + (ACE_ELEVEN - MINUS_ACE_SELF_SIZE);
        }
        return sumWithoutAce + aceCount;
    }

    private boolean isAce(Card card) {
        return card.getRank().equals(Rank.ACE);
    }

    private int getCardPoint(Card card) {
        return card.getRank().getPoint();
    }

    public Set<Card> getCards() {
        return Collections.unmodifiableSet(deck);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Deck deck1 = (Deck) o;

        return deck != null ? deck.equals(deck1.deck) : deck1.deck == null;
    }

    @Override
    public int hashCode() {
        return deck != null ? deck.hashCode() : 0;
    }
}
