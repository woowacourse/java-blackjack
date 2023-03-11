package blackjack.domain.card;

import java.util.List;

public class GamePoint implements Comparable<GamePoint> {

    public static final int BLACK_JACK_POINT_VALUE = 22;
    private static final int BUST = 0;
    private static final int MAX_GAME_POINT_VALUE = 21;
    private static final int ACE_BONUS_VALUE = 10;
    private static final int BLACK_JAVCK_CARD_SIZE = 2;
    private final int gamePoint;

    public GamePoint(final List<Card> cards) {
        this.gamePoint = getOptimizedValue(cards);
    }

    private int getOptimizedValue(List<Card> cards) {
        int originValue = getValueOf(cards);
        if (checkBlackJack(cards)) {
            return BLACK_JACK_POINT_VALUE;
        }
        if (originValue + ACE_BONUS_VALUE <= MAX_GAME_POINT_VALUE && containAce(cards)) {
            return originValue += ACE_BONUS_VALUE;
        }
        return checkWithBust(originValue);
    }

    private boolean checkBlackJack(final List<Card> cards) {
        final boolean blackJackSize = cards.size() == BLACK_JAVCK_CARD_SIZE;
        final boolean blackJackConcept = containAce(cards) && getValueOf(cards) == 11;
        return blackJackSize && blackJackConcept;
    }

    private int getValueOf(final List<Card> cards) {
        int value = 0;
        for (Card card : cards) {
            value += card.getCardNumber().getDefaultScore();
        }
        return value;
    }

    private boolean containAce(final List<Card> cards) {
        return cards.stream().anyMatch(card -> card.isAce());
    }

    private int checkWithBust(final int value) {
        if (value > MAX_GAME_POINT_VALUE) {
            return BUST;
        }
        return value;
    }

    public boolean isBlackJack() {
        return gamePoint == BLACK_JACK_POINT_VALUE;
    }

    public boolean isBusted() {
        return gamePoint == BUST;
    }

    public boolean isLowerThan(final int value) {
        return gamePoint <= value;
    }

    public int getValue() {
        return gamePoint;
    }

    @Override
    public int compareTo(final GamePoint other) {
        return Integer.compare(this.gamePoint, other.gamePoint);
    }

}
