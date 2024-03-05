package card;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Deck {

    private final Queue<Card> cards;

    Deck(List<Card> cards) {
        this.cards = new LinkedList<>(cards);
    }

    public Card draw() {
        return cards.poll();
    }
}
