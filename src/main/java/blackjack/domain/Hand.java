package blackjack.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Hand {

    private static final int ACE_ADDITIONAL_VALUE = 10;
    private static final int BLACKJACK_SCORE = 21;
    private static final int INITIAL_SIZE = 2;

    private final List<Card> cards;

    public Hand() {
        this.cards = new ArrayList<>();
    }

    public int calculate() {
        int sum = calculateWithDefaultAceNumber();

        if (hasAce() && isAceAdditionalValueAddable(sum)) {
            sum += ACE_ADDITIONAL_VALUE;
        }

        return sum;
    }

    public void put(Card card) {
        cards.add(card);
    }

    public List<Card> getCards() {
        return Collections.unmodifiableList(cards);
    }

    private int calculateWithDefaultAceNumber() {
        return cards.stream()
                .mapToInt(card -> card.getNumber().getValue())
                .sum();
    }

    private boolean hasAce() {
        return cards.stream()
                .anyMatch(Card::isAce);
    }

    private boolean isAceAdditionalValueAddable(int sum) {
        return sum + ACE_ADDITIONAL_VALUE <= BLACKJACK_SCORE;
    }

    public boolean isBlackjack() {
        return cards.size() == INITIAL_SIZE && calculate() == BLACKJACK_SCORE;
    }
}
