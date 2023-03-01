package blackjack.domain;

import java.util.List;

public class CardGamePoint {

    private static final int BURST = 0;
    private final int gamePoint;

    public CardGamePoint(final List<Card> cards) {
        int point = 0;
        point = calculateMaxValueOf(cards);
        if (isBurst(point) && containAceInCars(cards)) {
            this.gamePoint = getOptimizePoint(point, getCountOfAce(cards));
            return;
        }
        this.gamePoint = calculateBurst(point);
    }

    private boolean isBurst(final int point) {
        return calculateBurst(point) == BURST;
    }

    private int calculateMaxValueOf(final List<Card> cards) {
        int point = 0;
        for (Card card : cards) {
            point += optimize(card);
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

        return calculateBurst(optimizedPoint);
    }

    private boolean containAceInCars(List<Card> cards) {
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

    private int calculateBurst(final int point) {
        if (point > 21) {
            return BURST;
        }
        return point;
    }

    private int optimize(Card card) {
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
