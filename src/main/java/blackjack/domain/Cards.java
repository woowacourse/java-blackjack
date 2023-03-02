package blackjack.domain;

import java.util.ArrayList;
import java.util.List;

public class Cards {

    private final List<Card> cards;
    private final GamePoint point;

    public Cards(final List<Card> cards) {
        this.cards = new ArrayList<>(cards);
        this.point = new GamePoint(cards);
    }

    public Cards add(final Card card) {
        List<Card> cardList = new ArrayList<>(cards);
        cardList.add(card);
        return new Cards(cardList);
    }

    public boolean isBust() {
        return point.isBusted();
    }

    public GamePoint getPoint() {
        return point;
    }

    public int size() {
        return cards.size();
    }

    public boolean isLowerThan(final int value) {
        return point.isLowerThan(value);
    }

    public boolean isLowerThan(final GamePoint point) {
        return this.point.isLowerThan(point);
    }

    public boolean isGreaterThan(final GamePoint point) {
        return this.point.isGreaterThan(point);
    }

    public boolean havePointOf(final GamePoint point) {
        return this.point.isEqualTo(point);
    }
}
