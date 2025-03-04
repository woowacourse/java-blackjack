package domain;

import java.util.List;

public class CardGroup {

    private final List<Card> cards;

    public CardGroup(final List<Card> cards) {
        this.cards = cards;
    }

    public int calculateScore() {
        return cards.stream()
                .mapToInt(card -> card.getNumber().cardScore)
                .sum();
    }
}
