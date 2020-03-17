package domain.card;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class CardDeck {
    private final Queue<Card> cards;

    public CardDeck() {
        List<Card> cards = CardFactory.create();
        this.cards = new LinkedList<>(cards);
    }

    public Card draw() {
        return cards.poll();
    }
}
