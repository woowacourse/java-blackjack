package domain.card;

import java.util.ArrayList;
import java.util.List;

import domain.Number;

public final class Hand {
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
        int value = sumValue();

        if (hasAce() && value + Number.ACE_GAP.get() <= Number.BUST_BOUNDARY_VALUE.get()) {
            value += Number.ACE_GAP.get();
        }
        return value;
    }

    private boolean hasAce() {
        return hand.stream().anyMatch(Card::isAce);
    }

    private int sumValue() {
        return hand.stream()
            .mapToInt(Card::fetchValue)
            .sum();
    }

    public boolean isBust() {
        return calculateHandValueWithoutConsideringBust() > Number.BUST_BOUNDARY_VALUE.get();
    }

    public List<Card> getHand() {
        return new ArrayList<>(hand);
    }
}
