package blackjack.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Hand {

    private static final int ACE_ADDITIONAL_VALUE = 10;
    private static final int BLACKJACK_SCORE = 21;

    private final List<Card> hand;

    public Hand() {
        this.hand = new ArrayList<>();
    }

    public int calculate() {
        int sum = calculateWithDefaultAceNumber();

        if (hasAce() && isAceAdditionalValueAddable(sum)) {
            sum += ACE_ADDITIONAL_VALUE;
        }

        return sum;
    }

    public void put(Card card) {
        hand.add(card);
    }

    public List<Card> getCards() {
        return Collections.unmodifiableList(hand);
    }

    private int calculateWithDefaultAceNumber() {
        return hand.stream()
                .mapToInt(card -> card.getNumber().getValue())
                .sum();
    }

    private boolean hasAce() {
        return hand.stream()
                .anyMatch(Card::isAce);
    }

    private boolean isAceAdditionalValueAddable(int sum) {
        return sum + ACE_ADDITIONAL_VALUE <= BLACKJACK_SCORE;
    }
}
