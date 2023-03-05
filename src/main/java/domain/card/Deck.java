package domain.card;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Deck {

    public final Queue<Card> cards;

    public Deck(final List<Card> cards) {
        this.cards = new LinkedList<>(cards);
    }

    public Card drawCard() {
        return cards.poll();
    }

    public List<Card> getCards() {
        return List.copyOf(cards);
    }
}
