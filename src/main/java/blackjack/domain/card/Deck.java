package blackjack.domain.card;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;

public class Deck {

    private final Queue<Card> deck;

    public Deck() {
        this.deck = new LinkedList<>(shuffleDeck());
    }

    private List<Card> shuffleDeck() {
        final List<Card> cards = new ArrayList<>(CardsFactory.getCards());
        Collections.shuffle(cards);
        return cards;
    }

    public Card draw() {
        return deck.poll();
    }
}
