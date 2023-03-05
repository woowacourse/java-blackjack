package blackjack.domain;

import blackjack.domain.card.Card;

import java.util.List;

public final class GamePoint {

    private static final int BUST = 0;
    public static final int ACE = 1;
    public static final int ACE_UPPER = 11;
    public static final int FACE_CARD = 10;
    public static final int BLACK_JACK = 21;

    private final int gamePoint;

    public GamePoint(final List<Card> cards) {
        int point = calculateMaxPoint(cards);
        if (isBust(point) && containAceInCards(cards)) {
            this.gamePoint = getPoint(point, getCountOfAce(cards));
            return;
        }
        this.gamePoint = calculateBust(point);
    }

    private int calculateMaxPoint(final List<Card> cards) {
        int point = 0;
        for (Card card : cards) {
            point += transform(card);
        }
        return point;
    }

    private boolean containAceInCards(List<Card> cards) {
        return getCountOfAce(cards) != 0;
    }

    private boolean isBust(final int point) {
        return calculateBust(point) == BUST;
    }

    private int getPoint(final int point, final int aceCount) {
        int optimizedPoint = point;
        int remainAce = aceCount;
        while (optimizedPoint > BLACK_JACK && remainAce > 0) {
            optimizedPoint -= 10;
            remainAce -= 1;
        }

        return calculateBust(optimizedPoint);
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
