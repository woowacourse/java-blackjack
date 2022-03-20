package blackjack.domain.result;

import blackjack.domain.cards.Cards;
import blackjack.domain.cards.card.denomination.Denomination;
import java.util.Objects;

public final class Point {
    private static final int BLACKJACK_NUMBER = 21;
    private static final int ACE_MINUS_NUMBER = 10;
    private static final int MIN_COUNT = 0;

    private final int value;

    public Point(final Cards cards) {
        this.value = computeWithAce(cards.getRawPoint(),
                cards.getAceCount());
    }

    private int computeWithAce(int point, int aceCount) {
        if (point > BLACKJACK_NUMBER && aceCount > MIN_COUNT) {
            point -= ACE_MINUS_NUMBER;
            return computeWithAce(point, --aceCount);
        }
        return point;
    }

    public int get() {
        return value;
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
        return Objects.hash(value);
    }
}
