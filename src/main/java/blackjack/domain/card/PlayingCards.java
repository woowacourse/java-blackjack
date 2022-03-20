package blackjack.domain.card;

import java.util.*;

public class PlayingCards {

    private static final int BLACKJACK_POINT = 21;
    private static final int MAX_POINT = 21;
    private static final int ACE_BONUS = 10;
    private static final int BLACKJACK_SIZE = 2;

    private final List<PlayingCard> cards;

    public PlayingCards(List<PlayingCard> cards) {
        this.cards = new ArrayList<>(cards);
    }

    public PlayingCards() {
        this(new ArrayList<>());
    }

    public PlayingCards addCard(PlayingCard playingCard) {
        cards.add(playingCard);
        return this;
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

    public boolean isReady() {
        return cards.size() < BLACKJACK_SIZE;
    }

    public boolean isBlackjack() {
        return calculatePoints() == BLACKJACK_POINT && cards.size() == BLACKJACK_SIZE;
    }

    public boolean isMaxPoint() {
        return calculatePoints() == MAX_POINT;
    }

    public boolean isBust() {
        return calculatePoints() > BLACKJACK_POINT;
    }

    public List<PlayingCard> getCards() {
        return Collections.unmodifiableList(cards);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PlayingCards that = (PlayingCards) o;
        return Objects.equals(cards, that.cards);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cards);
    }
}
