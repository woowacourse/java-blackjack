package blackjack.domain.card;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Cards {

    private final int MAX_POINT = 21;
    private final int DIFFERENCE_POINT_OF_ACE = 10;
    private final int MINIMUM_ACE_AMOUNT = 1;
    private final String JOIN_DELIMITER = ", ";

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

    public boolean isOverThanMaxPoint() {
        return getPoint() > MAX_POINT;
    }

    public int getPoint() {
        int point = 0;
        for (Card card : cards) {
            point += card.getDenomination().getPoint();
        }

        return calculateAcePoint(point);
    }

    private int calculateAcePoint(int sumOfPoint) {
        int aceCount = getAceCount();
        while (sumOfPoint > MAX_POINT && aceCount >= MINIMUM_ACE_AMOUNT) {
            sumOfPoint -= DIFFERENCE_POINT_OF_ACE;
            aceCount--;
        }
        return sumOfPoint;
    }

    private int getAceCount() {
        return (int) cards.stream()
            .filter(card -> card.getDenomination().equals(Denomination.ACE))
            .count();
    }

    @Override
    public String toString() {
        return cards.stream()
            .map(Card::toString)
            .collect(Collectors.joining(JOIN_DELIMITER));
    }
}
