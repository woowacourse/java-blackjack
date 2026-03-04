package domain;

import java.util.List;

public class Hand {
    private static final int BURST_THRESHOLD = 21;

    private final List<Card> cards;

    Hand(List<Card> cards) {
        this.cards = cards;
    }

    public int calculateScore() {
        return cards.stream()
            .mapToInt(Card::toScore)
            .sum();
    }
}
