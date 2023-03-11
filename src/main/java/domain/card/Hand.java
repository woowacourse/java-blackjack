package domain.card;

import java.util.ArrayList;
import java.util.List;

public class Hand {
    private static final int HIGH_ACE_VALUE = 11;
    private static final int LOW_ACE_VALUE = 1;
    private static final int BUST_BOUNDARY_VALUE = 21;

    private final List<Card> hand;

    public Hand(List<Card> cards) {
        this.hand = cards;
    }

    public void addCard(Card card) {
        hand.add(card);
    }

    public int calculateHandValue() {
        int value = calculateHandValueWithoutBust();
        if (isBust()) {
            value = 0;
        }
        return value;
    }

    private int calculateHandValueWithoutBust() {
        int aceCount = countAce();
        int value = sumValue();

        value = chooseAceValue(value, aceCount);
        return value;
    }

    private int countAce() {
        return (int)hand.stream()
            .filter(Card::isAce)
            .count();
    }

    private int sumValue() {
        return hand.stream()
            .mapToInt(Card::fetchValue)
            .sum();
    }

    private int chooseAceValue(int value, int aceCount) {
        while (value > BUST_BOUNDARY_VALUE && aceCount > 0) {
            value -= HIGH_ACE_VALUE - LOW_ACE_VALUE;
            aceCount -= 1;
        }
        return value;
    }

    public boolean isBust() {
        return calculateHandValueWithoutBust() > BUST_BOUNDARY_VALUE;
    }

    public List<Card> getHand() {
        return new ArrayList<>(hand);
    }
}
