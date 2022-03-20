package domain.card;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Deck {

    private final Queue<Card> deck;

    public Deck(List<Card> cards) {
        Collections.shuffle(cards);
        this.deck = new LinkedList<>(cards);
    }

    public Card draw() {
        return deck.poll();
    }
}
