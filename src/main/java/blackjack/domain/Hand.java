package blackjack.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Hand {

    public static final int ACE_ADDITIONAL_VALUE = 10;
    public static final int BLACKJACK_SCORE = 21;

    private final List<Card> hand;

    public Hand() {
        this.hand = new ArrayList<>();
    }

    public int calculate() {
        int sum = calculateWithDefaultAceNumber();

        if (hasAce() && isAceAddable(sum)) {
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

    private boolean isAceAddable(int sum) {
        return sum + ACE_ADDITIONAL_VALUE > BLACKJACK_SCORE;
    }
}
