package domain;

import domain.card.Card;

import java.util.List;

public final class GamePoint {

    private static final int BUST = 0;
    public static final int ACE = 1;
    public static final int ACE_UPPER = 11;
    public static final int FACE_CARD = 10;
    public static final int BLACK_JACK = 21;

    private final int gamePoint;

    public GamePoint(final List<Card> cards) {
        int point = getPoint(calculateMaxPoint(cards), getCountOfAce(cards));
        this.gamePoint = calculateBust(point);
    }

    private int getPoint(int point, int aceCount) {
        while (point > BLACK_JACK && aceCount > 0) {
            point -= 10;
            aceCount -= 1;
        }
        return calculateBust(point);
    }

    private int calculateMaxPoint(final List<Card> cards) {
        return cards.stream()
                .mapToInt(this::transform)
                .sum();
    }

    private int getCountOfAce(final List<Card> cards) {
        return (int) cards.stream()
                .filter(card -> card.isSameAs(ACE))
                .count();
    }

    private int calculateBust(final int point) {
        if (point > BLACK_JACK) {
            return BUST;
        }
        return point;
    }

    private int transform(Card card) {
        if (card.isSameAs(ACE)) {
            return ACE_UPPER;
        }
        if (card.isOver(FACE_CARD)) {
            return FACE_CARD;
        }
        return card.getCardNumberValue();
    }

    public boolean isBusted() {
        return gamePoint == BUST;
    }

    public int getPoint() {
        return gamePoint;
    }

    public boolean isLowerThan(final int value) {
        return gamePoint <= value;
    }

    public boolean isLowerThan(final GamePoint point) {
        return gamePoint < point.gamePoint;
    }

    public boolean isGreaterThan(final GamePoint point) {
        return gamePoint > point.gamePoint;
    }

    public boolean isEqualTo(final GamePoint point) {
        return this.gamePoint == point.gamePoint;
    }
}
