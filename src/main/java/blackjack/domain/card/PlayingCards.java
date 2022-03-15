package blackjack.domain.card;

import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;

public class PlayingCards {

    public static final int BLACKJACK_POINT = 21;
    private static final int ACE_ELEVEN_POSSIBLE = 10;
    private static final int BLACKJACK_SIZE = 2;

    private final Set<PlayingCard> playingCards = new LinkedHashSet<>();

    public void addCard(PlayingCard playingCard) {
        playingCards.add(playingCard);
    }

    public int sumPoints() {
        int points = sumCardPoint();
        boolean aceExist = playingCards.stream()
                .anyMatch(PlayingCard::isAce);
        if (!aceExist) {
            return points;
        }
        return calculateAcePoint(points);
    }

    private int calculateAcePoint(int points) {
        if (points + ACE_ELEVEN_POSSIBLE <= BLACKJACK_POINT) {
            return points + ACE_ELEVEN_POSSIBLE;
        }
        return points;
    }

    private int sumCardPoint() {
        return playingCards.stream()
                .mapToInt(PlayingCard::getPoint)
                .sum();
    }

    public boolean isBust() {
        return sumPoints() > BLACKJACK_POINT;
    }

    public boolean isBlackJack() {
        return playingCards.size() == BLACKJACK_SIZE && sumPoints() == BLACKJACK_POINT;
    }

    public Set<PlayingCard> getCards() {
        return Collections.unmodifiableSet(playingCards);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PlayingCards playingCards = (PlayingCards) o;

        return this.playingCards != null ? this.playingCards.equals(playingCards.playingCards) : playingCards.playingCards == null;
    }

    @Override
    public int hashCode() {
        return playingCards != null ? playingCards.hashCode() : 0;
    }
}
