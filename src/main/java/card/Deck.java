package card;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Deck {

    private final Queue<Card> cards;

    public Deck(List<Card> cards) {
        this.cards = new LinkedList<>(cards);
    }

    public Card draw() {
        if (cards.isEmpty()) {
            throw new IllegalStateException("[ERROR] 덱이 비어있습니다.");
        }
        return cards.poll();
    }
}
