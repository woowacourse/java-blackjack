package blackjack.domain;

import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;

public class Deck {

    private static final int HAS_NOT_ACE = 0;
    private static final int ACE_ELEVEN_POSSIBLE = 10;

    private final Set<Card> deck = new LinkedHashSet<>();

    public void addCard(Card card) {
        deck.add(card);
    }

    public int sumPoints() {
        int points = deck.stream()
                .mapToInt(this::getCardPoint)
                .sum();
        int aceCount = (int) deck.stream()
                .filter(this::isAce)
                .count();
        if (aceCount == HAS_NOT_ACE) {
            return points;
        }
        return calculateAcePoint(points);
    }

    private int calculateAcePoint(int points) {
        if (points + ACE_ELEVEN_POSSIBLE <= Match.MAX_WINNER_POINT) {
            return points + ACE_ELEVEN_POSSIBLE;
        }
        return points;
    }

    private boolean isAce(Card card) {
        return card.getRank().equals(Symbols.ACE);
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
