package domain;

import java.util.ArrayDeque;
import java.util.List;
import java.util.Queue;

public class Deck {
    private final Queue<Card> cards = new ArrayDeque<>();

    public Deck(List<Card> cards) {
        this.cards.addAll(cards);
    }

    public List<Card> getCards() {
        return List.copyOf(cards);
    }

    public Card drawCard() {
        return cards.poll();
    }
}
