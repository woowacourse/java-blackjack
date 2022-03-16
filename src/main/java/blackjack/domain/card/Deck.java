package blackjack.domain.card;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Deck {

    private final Queue<Card> deck;

    private Deck(Queue<Card> deck) {
        this.deck = deck;
    }

    public static Deck from(CardOrderStrategy strategy) {
        final List<Card> cards = strategy.generate();
        return new Deck(new LinkedList<>(cards));
    }

    public Card drawCard() {
        return deck.poll();
    }
}
