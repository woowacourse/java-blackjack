package domain;

import java.util.List;

public class Cards {

    private final List<Card> cards;

    public Cards(List<Card> cards) {
        this.cards = cards;
    }

    public int calculateTotalSum() {
        return cards.stream()
                .mapToInt(card -> card.getNumber().getValue())
                .sum();
    }
}
