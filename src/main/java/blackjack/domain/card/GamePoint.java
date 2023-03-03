package blackjack.domain.card;

import java.util.List;

public class GamePoint implements Comparable<GamePoint>{

    private static final int BUST = 0;
    private static final CardNumber ACE = CardNumber.of(1);
    private static final CardNumber TEN = CardNumber.of(10);
    private final int gamePoint;

    public GamePoint(final List<Card> cards) {
        this.gamePoint = getOptimizeValueOf(cards);
    }

    private Integer getOptimizeValueOf(final List<Card> cards) {
        int point = calculateMaxPoint(cards);
        if (canOptimize(cards, point)) {
            return getGamePoint(point, getCountOfAce(cards));
        }
        return calculateWithBust(point);
    }

    private int calculateMaxPoint(final List<Card> cards) {
        int point = 0;
        for (Card card : cards) {
            point += transform(card);
        }
        return point;
    }

    private int transform(Card card) {
        if (isAce(card)) {
            return 11;
        }
        if (card.haveOverNumberThan(TEN)) {
            return 10;
        }
        return card.getCardNumberValue();
    }

    private boolean isAce(final Card card) {
        return card.haveCardNumberOf(ACE);
    }

    private boolean canOptimize(final List<Card> cards, final int point) {
        return isBust(point) && containAceInCards(cards);
    }

    private boolean isBust(final int point) {
        return calculateWithBust(point) == BUST;
    }

    private int calculateWithBust(final int point) {
        if (point > 21) {
            return BUST;
        }
        return point;
    }

    private boolean containAceInCards(List<Card> cards) {
        if (getCountOfAce(cards) != 0) {
            return true;
        }
        return false;
    }

    private int getCountOfAce(final List<Card> cards) {
        return (int) cards.stream()
                .filter((card) -> isAce(card))
                .count();
    }

    private int getGamePoint(final int point, final int aceCount) {
        int optimizedPoint = point;
        int remainAce = aceCount;
        while (optimizedPoint > 21 && remainAce > 0) {
            optimizedPoint -= 10;
            remainAce -= 1;
        }
        return calculateWithBust(optimizedPoint);
    }

    public boolean isBusted() {
        return gamePoint == BUST;
    }

    public int getGamePoint() {
        return gamePoint;
    }

    public boolean isLowerThan(final int value) {
        return gamePoint <= value;
    }

    @Override
    public int compareTo(final GamePoint other) {
        return Integer.compare(this.gamePoint, other.gamePoint);
    }
}
