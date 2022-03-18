package blackjack.domain.card;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Deck {

    private final LinkedList<Card> deck;

    private Deck(LinkedList<Card> deck) {
        this.deck = deck;
    }

    public static Deck create() {
        List<Card> cards = Card.initializeDeck();
        return new Deck(new LinkedList<>(cards));
    }

    public static Deck of(LinkedList<Card> deck) {
        Collections.shuffle(deck);
        return new Deck(deck);
    }

    public Card distributeCard() {
        return deck.poll();
    }

    public LinkedList<Card> getDeck() {
        return deck;
    }
}
