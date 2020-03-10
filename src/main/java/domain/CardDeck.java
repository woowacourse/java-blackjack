package domain;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class CardDeck {
    private final Queue<Card> cards;

    public CardDeck() {
        List<Card> cards = CardFactory.create();
        Collections.shuffle(cards);
        this.cards = new LinkedList<>(cards);
    }
}
