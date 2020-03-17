package blackjack.domain.user;

import blackjack.domain.card.Card;
import blackjack.domain.card.component.CardNumber;

import java.util.List;

public class Point implements Comparable<Point> {
    private static int BALCK_JACK = 21;
    private int point;

    public Point(int point) {
        this.point = point;
    }

    public Point (List<Card> cards) {
        this.point = cards.stream()
                .mapToInt(x -> x.getCardPoint())
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
        if ((point < BALCK_JACK) && (aceCount > 0)) {
            point += CardNumber.ACE_DIFF;
        }
    }

    public int diffWithBlackJack() {
        return BALCK_JACK - this.point;
    }

    public boolean isBust() {
        return this.point > BALCK_JACK;
    }

    public boolean isNotBust() {
        return !isBust();
    }

    public int getPoint() {
        return this.point;
    }

    @Override
    public int compareTo(Point point) {
        return this.getPoint() - point.getPoint();
    }
}
