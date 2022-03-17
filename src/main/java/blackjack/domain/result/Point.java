package blackjack.domain.result;

import blackjack.domain.cards.Cards;
import blackjack.domain.cards.card.denomination.Denomination;

public final class Point implements Comparable<Point> {
    private static final int BLACKJACK_NUMBER = 21;
    private static final int ACE_MINUS_NUMBER = 10;
    private static final int MIN_ACE_COUNT = 0;

    private final int value;

    private Point(final int number) {
        this.value = number;
    }

    private Point(final Cards cards) {
        this.value = computeWithAce(cards.getRawPoint(), cards.getDenominationCount(Denomination.ACE));
    }

    private static int computeWithAce(int point, int aceCount) {
        if (point > BLACKJACK_NUMBER && aceCount > MIN_ACE_COUNT) {
            point -= ACE_MINUS_NUMBER;
            return computeWithAce(point, --aceCount);
        }
        return point;
    }

    public static Point fromCards(final Cards cards) {
        return new Point(cards);
    }

    public static Point fromValue(int number) {
        return new Point(number);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Point point = (Point) o;
        return value == point.value;
    }

    @Override
    public int hashCode() {
        return value;
    }

    @Override
    public int compareTo(Point other) {
        return Integer.compare(this.value, other.value);
    }
}
