package domain.card;

import java.util.ArrayList;
import java.util.List;

public final class Hand {
    private static final int HIGH_ACE_VALUE = 11;
    private static final int LOW_ACE_VALUE = 1;
    private static final int BUST_BOUNDARY_VALUE = 21;

    private final List<Card> hand;

    public Hand(final List<Card> cards) {
        this.hand = cards;
    }

    public void addCard(final Card card) {
        hand.add(card);
    }

    public int calculateHandValue() {
        int value = calculateHandValueWithoutConsideringBust();
        if (isBust()) {
            value = 0;
        }
        return value;
    }

    private int calculateHandValueWithoutConsideringBust() {
        boolean hasAce = hand.stream().anyMatch(Card::isAce);
        int value = sumValue();

        if (hasAce && value + HIGH_ACE_VALUE - LOW_ACE_VALUE <= BUST_BOUNDARY_VALUE) {
            value += HIGH_ACE_VALUE - LOW_ACE_VALUE;
        }
        return value;
    }

    private int sumValue() {
        return hand.stream()
            .mapToInt(Card::fetchValue)
            .sum();
    }

    public boolean isBust() {
        return calculateHandValueWithoutConsideringBust() > BUST_BOUNDARY_VALUE;
    }

    public List<Card> getHand() {
        return new ArrayList<>(hand);
    }
}
