package blackjack.domain;

import java.util.Collections;
import java.util.List;

public class Deck {

    private List<Card> cards;

    private Deck() {
        this.cards = Card.createCards();
    }

    public static Deck create() {
        Deck deck = new Deck();
        deck.shuffle();
        return deck;
    }

    private void shuffle() {
        Collections.shuffle(cards);
    }

    public Card pop() {
        if (cards.isEmpty()) {
            this.cards = Card.createCards();
        }
        return cards.remove(0);
    }

    public int size() {
        return cards.size();
    }
}
