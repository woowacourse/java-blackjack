package blackjack.domain.card;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Deck {

    private final LinkedList<Card> deck;

    private Deck(LinkedList<Card> deck) {
        this.deck = deck;
    }

    public Deck() {
        this(new LinkedList<>(Card.initializeDeck()));
    }

    public static Deck create() {
        List<Card> cards = Card.initializeDeck();
        Collections.shuffle(cards);
        return new Deck(new LinkedList<>(cards));
    }

    public Card distributeCard() {
        return deck.poll();
    }

    public List<Card> getDeck() {
        return Collections.unmodifiableList(deck);
    }
}
