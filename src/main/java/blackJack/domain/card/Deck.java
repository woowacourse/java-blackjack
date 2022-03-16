package blackJack.domain.card;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Deck {

    private final Queue<Card> deck;

    private Deck(LinkedList<Card> deck) {
        Collections.shuffle(deck);
        this.deck = deck;
    }

    public static Deck create() {
        List<Card> cards = Card.initializeDeck();
        return new Deck(new LinkedList<>(cards));
    }

    public Card distributeCard() {
        return deck.poll();
    }
}
