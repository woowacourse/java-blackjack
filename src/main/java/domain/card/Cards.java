package domain.card;

import domain.GamePoint;

import java.util.ArrayList;
import java.util.List;

public final class Cards {

    private final List<Card> cards;
    private final GamePoint gamePoint;

    public Cards(final List<Card> cards) {
        this.cards = new ArrayList<>(cards);
        this.gamePoint = new GamePoint(cards);
    }

    public Cards add(final Card card) {
        List<Card> cardList = new ArrayList<>(cards);
        cardList.add(card);
        return new Cards(cardList);
    }

    public boolean isBusted() {
        return gamePoint.isBusted();
    }

    public GamePoint getGamePoint() {
        return gamePoint;
    }

    public int size() {
        return cards.size();
    }

    public boolean isLowerThan(final int value) {
        return gamePoint.isLowerThan(value);
    }

    public boolean isLowerThan(final GamePoint point) {
        return this.gamePoint.isLowerThan(point);
    }

    public boolean isGreaterThan(final GamePoint point) {
        return this.gamePoint.isGreaterThan(point);
    }

    public boolean havePointOf(final GamePoint point) {
        return this.gamePoint.isEqualTo(point);
    }

    public List<Card> getCards() {
        return cards;
    }

    public boolean isEmpty() {
        return cards.isEmpty();
    }
}
