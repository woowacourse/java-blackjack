package model.card;

import java.util.ArrayList;
import java.util.List;

public class Cards {

    private final List<Card> cards;

    public Cards(List<Card> cards) {
        this.cards = List.copyOf(cards);
    }

    public int calculateTotalNumbers() {
        return cards.stream()
            .mapToInt(card -> card.getNumber().getScore())
            .sum();
    }

    public Cards add(Card card) {
        List<Card> addedCards = new ArrayList<>(cards);
        addedCards.add(card);
        return new Cards(addedCards);
    }

    public Cards addAll(List<Card> cardsElement) {
        List<Card> addedCards = new ArrayList<>(cards);
        addedCards.addAll(cardsElement);
        return new Cards(addedCards);
    }

    public int size() {
        return cards.size();
    }

    public List<Card> getCards() {
        return cards;
    }
}
