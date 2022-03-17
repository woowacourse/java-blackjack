package blackjack.domain.card;

import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

public class PlayingCards {

    private static final int BLACKJACK_POINT = 21;
    private static final int ACE_BONUS = 10;
    private static final int BLACKJACK_SIZE = 2;

    private final Set<PlayingCard> cards;

    public PlayingCards(Set<PlayingCard> cards) {
        this.cards = new HashSet<>(cards);
    }

    public PlayingCards() {
        this(new LinkedHashSet<>());
    }

    public void addCard(PlayingCard playingCard) {
        cards.add(playingCard);
    }

    public int calculatePoints() {
        int points = sumCardPoint();
        boolean aceExist = cards.stream()
                .anyMatch(PlayingCard::isAce);
        if (!aceExist) {
            return points;
        }
        return calculateAcePoint(points);
    }

    private int calculateAcePoint(int points) {
        if (points + ACE_BONUS <= BLACKJACK_POINT) {
            return points + ACE_BONUS;
        }
        return points;
    }

    private int sumCardPoint() {
        return cards.stream()
                .mapToInt(PlayingCard::getPoint)
                .sum();
    }

    public boolean isBust() {
        return calculatePoints() > BLACKJACK_POINT;
    }

    public boolean isHit() {
        return calculatePoints() < BLACKJACK_POINT;
    }

    public boolean isBlackJack() {
        return cards.size() == BLACKJACK_SIZE && calculatePoints() == BLACKJACK_POINT;
    }

    public Set<PlayingCard> getCards() {
        return Collections.unmodifiableSet(cards);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PlayingCards playingCards = (PlayingCards) o;

        return this.cards != null ? this.cards.equals(playingCards.cards) : playingCards.cards == null;
    }

    @Override
    public int hashCode() {
        return cards != null ? cards.hashCode() : 0;
    }
}
