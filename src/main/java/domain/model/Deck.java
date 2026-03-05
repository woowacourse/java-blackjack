package domain.model;

import java.util.List;

public class Deck {

    private List<Card> cards;

    private Deck(List<Card> deck) {
        this.cards = deck;
    }

    public static Deck of(List<Card> cards) {
        return new Deck(cards);
    }

    public void append(Card card) {
        this.cards.add(card);
    }

    public int getSize() {
        return cards.size();
    }
}
