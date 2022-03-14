package blackjack.domain;

import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;

public class Deck {

    private static final int HAS_NOT_ACE = 0;
    private static final int ACE_ELEVEN_POSSIBLE = 10;
    private static final int BLACKJACK_SIZE = 2;

    private final Set<Card> deck = new LinkedHashSet<>();

    public void addCard(Card card) {
        deck.add(card);
    }

    public int sumPoints() {
        int points = sumCardPoint();
        int aceCount = (int) deck.stream()
                .filter(Card::isAce)
                .count();
        if (aceCount == HAS_NOT_ACE) {
            return points;
        }
        return calculateAcePoint(points);
    }

    public boolean sumBlackJack() {
        return deck.size() == BLACKJACK_SIZE && sumPoints() == Match.MAX_WINNER_POINT;
    }

    private int calculateAcePoint(int points) {
        if (points + ACE_ELEVEN_POSSIBLE <= Match.MAX_WINNER_POINT) {
            return points + ACE_ELEVEN_POSSIBLE;
        }
        return points;
    }

    private int sumCardPoint() {
        int points = 0;
        for (Card card : deck) {
            points = card.sumPoint(points);
        }
        return points;
    }

    public Set<Card> getCards() {
        return Collections.unmodifiableSet(deck);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Deck deck1 = (Deck) o;

        return deck != null ? deck.equals(deck1.deck) : deck1.deck == null;
    }

    @Override
    public int hashCode() {
        return deck != null ? deck.hashCode() : 0;
    }
}
