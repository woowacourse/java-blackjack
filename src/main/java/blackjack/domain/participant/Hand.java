package blackjack.domain.participant;

import blackjack.domain.card.Card;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Hand {

    private static final int ACE_ADDITIONAL_VALUE = 10;
    private static final int BLACKJACK_SCORE = 21;
    private static final int INITIAL_SIZE = 2;

    private final List<Card> cards;

    Hand() {
        this.cards = new ArrayList<>();
    }

    public boolean isBlackjack() {
        return cards.size() == INITIAL_SIZE && calculate() == BLACKJACK_SCORE;
    }

    public List<Card> getCards() {
        return Collections.unmodifiableList(cards);
    }

    int calculate() {
        int sum = calculateWithDefaultAceNumber();

        if (isAceAdditionalValueAddable(sum)) {
            return sum + ACE_ADDITIONAL_VALUE;
        }
        return sum;
    }

    void put(Card card) {
        cards.add(card);
    }

    private int calculateWithDefaultAceNumber() {
        return cards.stream()
                .mapToInt(card -> card.getNumber().getValue())
                .sum();
    }

    private boolean isAceAdditionalValueAddable(int sum) {
        return hasAce() && sum + ACE_ADDITIONAL_VALUE <= BLACKJACK_SCORE;
    }

    private boolean hasAce() {
        return cards.stream()
                .anyMatch(Card::isAce);
    }
}
