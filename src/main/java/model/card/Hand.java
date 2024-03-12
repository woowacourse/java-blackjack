package model.card;

import java.util.ArrayList;
import java.util.List;

public class Hand {

    private final List<Card> cards;

    public Hand(List<Card> cards) {
        this.cards = List.copyOf(cards);
    }

    public int calculateTotalNumbers() {
        return cards.stream()
            .mapToInt(card -> card.getNumber().getScore())
            .sum();
    }

    public Hand add(Card card) {
        List<Card> addedCards = new ArrayList<>(cards);
        addedCards.add(card);
        return new Hand(addedCards);
    }

    public Hand addAll(List<Card> cards) {
        List<Card> addedCards = new ArrayList<>(this.cards);
        addedCards.addAll(cards);
        return new Hand(addedCards);
    }

    public int size() {
        return cards.size();
    }

    public List<Card> getCards() {
        return cards;
    }
}
