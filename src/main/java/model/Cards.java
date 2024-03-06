package model;

import java.util.ArrayList;
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

    public Cards add(Card card) {
        List<Card> addedCards = new ArrayList<>(this.cards);
        addedCards.add(card);
        return new Cards(addedCards);
    }

    public int size() {
        return cards.size();
    }
}
