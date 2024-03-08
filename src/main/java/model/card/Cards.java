package model.card;

import java.util.ArrayList;
import java.util.List;

public class Cards {

    private final List<Card> elements;

    public Cards(List<Card> cards) {
        elements = List.copyOf(cards);
    }

    public int calculateTotalNumbers() {
        return elements.stream()
            .mapToInt(card -> card.getNumber().getScore())
            .sum();
    }

    public Cards add(Card card) {
        List<Card> addedCards = new ArrayList<>(elements);
        addedCards.add(card);
        return new Cards(addedCards);
    }

    public Cards addAll(List<Card> cardsElement) {
        List<Card> addedCards = new ArrayList<>(elements);
        addedCards.addAll(cardsElement);
        return new Cards(addedCards);
    }

    public int size() {
        return elements.size();
    }

    public List<Card> getElements() {
        return elements;
    }
}
