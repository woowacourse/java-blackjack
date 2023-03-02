package blackjack.domain;

import java.util.List;

public class GamePoint {

    private static final int BUST = 0;
    private final int gamePoint;

    public GamePoint(final List<Card> cards) {
        int point = calculateMaxPoint(cards);
        if (isBust(point) && containAceInCards(cards)) {
            this.gamePoint = getOptimizePoint(point, getCountOfAce(cards));
            return;
        }
        this.gamePoint = calculateBust(point);
    }

    private boolean isBust(final int point) {
        return calculateBust(point) == BUST;
    }

    private int calculateMaxPoint(final List<Card> cards) {
        int point = 0;
        for (Card card : cards) {
            point += transform(card);
        }
        return point;
    }

    private int getOptimizePoint(final int point, final int aceCount) {
        int optimizedPoint = point;
        int remainAce = aceCount;
        while (optimizedPoint > 21 && remainAce > 0) {
            optimizedPoint -= 10;
            remainAce -= 1;
        }

        return calculateBust(optimizedPoint);
    }

    private boolean containAceInCards(List<Card> cards) {
        if (getCountOfAce(cards) != 0) {
            return true;
        }
        return false;
    }

    private static int getCountOfAce(final List<Card> cards) {
        return (int) cards.stream()
                .filter((card) -> card.isAce())
                .count();
    }

    private int calculateBust(final int point) {
        if (point > 21) {
            return BUST;
        }
        return point;
    }

    private int transform(Card card) {
        if (card.isAce()) {
            return 11;
        }
        if (card.isOverTen()) {
            return 10;
        }
        return card.getCardNumberValue();
    }

    public int getOptimizePoint() {
        return gamePoint;
    }
}
