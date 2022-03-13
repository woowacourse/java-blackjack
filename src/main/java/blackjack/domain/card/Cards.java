package blackjack.domain.card;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

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
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Cards)) {
            return false;
        }
        Cards cards1 = (Cards) o;
        return Objects.equals(cards, cards1.cards);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cards);
    }

    @Override
    public String toString() {
        return "Cards{" +
                "cards=" + cards +
                '}';
    }
}
