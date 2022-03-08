package model;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class CardDeck {
    private final Queue<Card> deck;

    public CardDeck() {
        this.deck = new LinkedList<>(initCardDeck());
    }

    private List<Card> initCardDeck() {
        List<Card> cards = Card.getAllCards();
        Collections.shuffle(cards);
        return cards;
    }

    public boolean isEmpty() {
        return deck.isEmpty();
    }

    public Card drawCard() {
        return deck.poll();
    }
}
