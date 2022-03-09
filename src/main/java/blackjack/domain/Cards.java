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

        for (int i = 0; i < countAce(); i++) {
            sum += getAceAdditionalValue(sum);
        }
        return sum;
    }

    private int countAce() {
        return (int) cards.stream()
                .filter(card -> card.getCardNumber().equals(CardNumber.ACE))
                .count();
    }

    private int getAceAdditionalValue(int sum) {
        if (sum + ACE_ADDITIONAL_VALUE <= MAX_SCORE) {
            return ACE_ADDITIONAL_VALUE;
        }
        return 0;
    }
}
