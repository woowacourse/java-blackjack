package blackjack.domain.card;

import java.util.ArrayList;
import java.util.List;

public class Cards {

    private final int BLACKJACK = 21;
    private final int DIFFERENCE_POINT_OF_ACE = 10;
    private final int MINIMUM_ACE_AMOUNT = 1;

    private final List<Card> cards;

    public Cards() {
        this.cards = new ArrayList<>();
    }

    public Cards(List<Card> value) {
        this.cards = value;
    }

    public int sum() {
        return cards.stream()
            .mapToInt(Card::point)
            .sum();
    }

    public boolean hasAce() {
        return cards.stream()
            .anyMatch(Card::isAce);
    }

    public boolean isBlackJack() {
        return 11 == sum() && hasAce();
    }

    public boolean isReady() {
        return cards.size() < 2;
    }

    public static Cards create() {
        return new Cards();
    }

    public Cards add(Card card) {
        final List<Card> newValue = new ArrayList<>(cards);
        newValue.add(card);
        return new Cards(newValue);
    }

    public boolean isBust2() {
        return sum() > 21;
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
        return List.copyOf(cards);
    }
}
