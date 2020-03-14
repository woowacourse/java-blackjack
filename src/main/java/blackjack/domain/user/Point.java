package blackjack.domain.user;

import blackjack.domain.card.Card;
import blackjack.domain.card.component.CardNumber;

import java.util.List;

public class Point {
    private static int BUST_NUMBER = 21;
    private int point;

    public Point() {
        this.point = 0;
    }

    public Point(int point) {
        this.point = point;
    }

    public void computePoint(List<Card> cards) {
        this.point = cards.stream()
                .mapToInt(x -> x.getCardNumber().getNumber())
                .sum();
        handleAce(cards);
    }

    private static int getAceCount(List<Card> cards) {
        int aceCount = (int) cards.stream()
                .filter(Card::isAce)
                .count();
        return aceCount;
    }

    private void handleAce(List<Card> cards) {
        int aceCount = getAceCount(cards);
        while (aceCount-- > 0) {
            if (this.point > BUST_NUMBER) {
                this.point -= CardNumber.ACE_DIFF;
            }
        }
    }

    public boolean isBust() {
        return this.point == 21;
    }

    public boolean overBust() {
        return this.point > 21;
    }

    public boolean isBiggerThan(Point point) {
        return this.point > point.getPoint();
    }

    public boolean isEqual(Point point) {
        return this.point == point.getPoint();
    }

    public int getPoint() {
        return this.point;
    }
}
