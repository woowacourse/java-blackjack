package blackjack.domain.user;

import blackjack.domain.card.Card;
import blackjack.domain.card.component.CardNumber;

import javax.print.DocFlavor;
import java.util.List;

public class Point implements Comparable<Point> {
    private static int BALCK_JACK = 21;
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
            if (this.point > BALCK_JACK) {
                this.point -= CardNumber.ACE_DIFF;
            }
        }
    }

    public boolean isBlackJack() {
        return this.point == BALCK_JACK;
    }

    public boolean isBust() {
        return this.point > BALCK_JACK;
    }

    public int getPoint() {
        return this.point;
    }

    @Override
    public int compareTo(Point point) {
        if (this.getPoint() > point.getPoint()) {
            return  1;
        }

        if (this.getPoint() < point.getPoint()) {
            return  -1;
        }
        return 0;
    }
}
