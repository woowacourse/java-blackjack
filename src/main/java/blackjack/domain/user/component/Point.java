package blackjack.domain.user.component;

import blackjack.domain.card.Card;
import blackjack.domain.card.component.CardNumber;

import java.util.List;

public class Point implements Comparable<Point> {
    private static int BLACK_JACK = 21;
    private final boolean isBalckJack;
    private int point;

    public Point(int point, boolean isBalckJack) {
        this.point = point;
        this.isBalckJack = isBalckJack;
    }

    public Point (List<Card> cards) {
        point = cards.stream()
                .mapToInt(x -> x.getCardPoint())
                .sum();
        if (hasAce(cards)) {
            handleAce(cards);
        }
        isBalckJack = (cards.size() == 2 && point == BLACK_JACK);
    }

    private static boolean hasAce(List<Card> cards) {
        int aceCount = (int) cards.stream()
                .filter(Card::isAce)
                .count();
        return aceCount > 0;
    }

    private void handleAce(List<Card> cards) {
        if (point < BLACK_JACK) {
            point += CardNumber.ACE_DIFF;
        }
    }

    public int diffWithBlackJack() {
        return BLACK_JACK - this.point;
    }

    public boolean isBust() {
        return this.point > BLACK_JACK;
    }

    public boolean isNotBust() {
        return !isBust();
    }

    public boolean isBalckJack() {
        return this.isBalckJack;
    }

    public int getPoint() {
        return this.point;
    }

    @Override
    public int compareTo(Point point) {
        return this.getPoint() - point.getPoint();
    }
}
