package blackjack.domain.card;

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
        List<Card> newCardList = new ArrayList<>(cards);
        newCardList.add(card);
        return new Cards(newCardList);
    }

    public boolean isBust() {
        return point.isBusted();
    }

    public int size() {
        return cards.size();
    }

    public boolean haveLowerGamePointThan(final int value) {
        return point.isLowerThan(value);
    }

    public List<Card> getCards() {
        return cards;
    }

    public GamePoint getPoint() {
        return new GamePoint(cards);
    }
}
