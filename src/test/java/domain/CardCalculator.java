package domain;

import java.util.List;

public class CardCalculator {
    public int sum(final List<Card> cards) {
        return cards.stream()
                .mapToInt(Card::getCardNumber)
                .sum();
    }
}
