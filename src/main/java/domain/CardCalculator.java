package domain;

import java.util.List;

public class CardCalculator {

    private static final int BLACK_JACK = 21;


    public int sum(final List<Card> cards) {
        int total = cards.stream()
                .mapToInt(Card::getCardNumber)
                .sum();
        if (total <= BLACK_JACK && containAce(cards)) {
            return total + 10;
        }
        return total;
    }

    private boolean containAce(final List<Card> cards) {
        return cards.stream()
                .anyMatch(Card::isAce);
    }
}
