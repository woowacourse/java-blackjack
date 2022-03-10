package blackjack.domain.card;

import blackjack.util.Constants;
import java.util.List;

public class Point {
    public static final int ACE_MINUS_NUMBER = 10;
    public static final int MIN_ACE_COUNT = 0;
    private final int value;

    private Point(List<Card> rawCards) {
        this.value = computeAce(getRawPoint(rawCards), getAceCount(rawCards));
    }

    public static Point of(List<Card> rawCards) {
        return new Point(rawCards);
    }

    public int get() {
        return value;
    }

    private int computeAce(int point, int aceCount) {
        if (point > Constants.BLACKJACK_NUMBER && aceCount > MIN_ACE_COUNT) {
            point -= ACE_MINUS_NUMBER;
            return computeAce(point, --aceCount);
        }
        return point;
    }

    private int getRawPoint(List<Card> cards) {
        return cards.stream()
                .mapToInt(card -> card.getDenomination().getPoint())
                .sum();
    }

    private int getAceCount(List<Card> cards) {
        return (int) cards.stream()
                .filter(Card::isAce)
                .count();
    }
}
