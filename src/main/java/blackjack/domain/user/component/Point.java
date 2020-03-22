package blackjack.domain.user.component;

import blackjack.domain.card.Cards;

public class Point implements Comparable<Point> {
    private final boolean isBalckJack;
    private int point;
    public static int BLACK_JACK = 21;

    public Point(int point, boolean isBalckJack) {
        this.point = point;
        this.isBalckJack = isBalckJack;
    }

    public Point (Cards cards) {
        point = cards.computePoint();
        isBalckJack = (cards.getSize() == 2 && point == BLACK_JACK);
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
