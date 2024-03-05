package blackjack.model;

import java.util.List;

public class Cards {
    private static final int BOUNDARY_SCORE = 11;
    private static final int EXTRA_SCORE = 10;

    private final List<Card> cards;

    public Cards(List<Card> cards) {
        this.cards = cards;
    }

    public int calculateScore() {
        int score = calculate();
        if (hasAce() && score <= BOUNDARY_SCORE) {
            return score + EXTRA_SCORE;
        }
        return score;
    }

    private boolean hasAce() {
        return cards.stream()
                .anyMatch(Card::isAce);
    }

    private int calculate() {
        return cards.stream()
                .map(Card::getCardNumber)
                .mapToInt(CardNumber::getScore)
                .sum();
    }
}
