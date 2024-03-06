package model;

import java.util.List;

public class Cards {

    private final List<Card> cards;

    public Cards(List<Card> cards) {
        this.cards = List.copyOf(cards);
    }

    public int calculateTotalNumbers() {
        return cards.stream()
            .mapToInt(card -> card.getNumber().getValue())
            .sum();
    }
}
