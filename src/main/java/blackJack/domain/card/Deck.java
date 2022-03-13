package blackJack.domain.card;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Deck {

    private final Queue<Card> deck;

    public Deck() {
        List<Card> cards = Card.newCards();
        Collections.shuffle(cards);
        deck = new LinkedList<>(cards);
    }

    public Card getCard() {
        return deck.poll();
    }
}
