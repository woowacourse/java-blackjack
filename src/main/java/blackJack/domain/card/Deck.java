package blackJack.domain.card;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Deck {

    private final Queue<Card> deck;

    private Deck(LinkedList<Card> deck) {
        this.deck = deck;
    }

    public static Deck createDeck() {
        List<Card> cards = Card.initializeDeck();
        Collections.shuffle(cards);
        return new Deck(new LinkedList<>(cards));
    }

    public Card getCard() {
        return deck.poll();
    }
}
