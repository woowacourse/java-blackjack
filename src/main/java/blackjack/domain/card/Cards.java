package blackjack.domain.card;

import java.util.ArrayList;
import java.util.List;

public class Cards {

    private final int BLACKJACK = 21;
    private final int DIFFERENCE_POINT_OF_ACE = 10;
    private final int MINIMUM_ACE_AMOUNT = 1;

    private final List<Card> cards;

    private Cards() {
        this.cards = new ArrayList<>();
    }

    public static Cards create() {
        return new Cards();
    }

    public void add(Card card) {
        cards.add(card);
    }

    public int size() {
        return cards.size();
    }

    public boolean isBust() {
        return getPoint() > BLACKJACK;
    }

    public int getPoint() {
        int point = 0;
        for (Card card : cards) {
            point += card.getDenomination().getPoint();
        }

        return calculateAcePoint(point);
    }

    private int calculateAcePoint(int sumOfPoint) {
        int aceCount = countAce();
        while (sumOfPoint > BLACKJACK && aceCount >= MINIMUM_ACE_AMOUNT) {
            sumOfPoint -= DIFFERENCE_POINT_OF_ACE;
            aceCount--;
        }
        return sumOfPoint;
    }

    private int countAce() {
        return (int) cards.stream()
            .filter(card -> card.getDenomination().equals(Denomination.ACE))
            .count();
    }

    public List<Card> getCards() {
        return cards;
    }
}
