package blackjack.domain;

import java.util.Collections;
import java.util.List;

public class Cards {

    private static final int ACE_ADDITIONAL_VALUE = 10;
    private static final int MAX_SCORE = 21;

    private final List<Card> cards;

    public Cards(List<Card> cards) {
        this.cards = cards;
    }

    public void add(Card card) {
        cards.add(card);
    }

    public List<Card> get() {
        return Collections.unmodifiableList(cards);
    }

    public int getTotalScore() {
        int sum = cards.stream()
                .mapToInt(value -> value.getCardNumber().getValue())
                .sum();

        if (hasAce() && canAddAceAdditionalValue(sum)) {
            return sum + ACE_ADDITIONAL_VALUE;
        }
        return sum;
    }

    private boolean hasAce() {
        return cards.stream()
                .anyMatch(card -> card.getCardNumber().equals(CardNumber.ACE));
    }

    private boolean canAddAceAdditionalValue(int sum) {
        return sum + ACE_ADDITIONAL_VALUE <= MAX_SCORE;
    }

    public boolean containsCardNumber(CardNumber number) {
        return cards.stream()
                .anyMatch(card -> card.getCardNumber() == number);
    }

    @Override
    public String toString() {
        return "Cards{" +
                "cards=" + cards +
                '}';
    }
}
