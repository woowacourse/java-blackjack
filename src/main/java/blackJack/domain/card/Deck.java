package blackJack.domain.card;

import java.util.ArrayDeque;
import java.util.Collections;
import java.util.Deque;
import java.util.List;
import java.util.Queue;

public class Deck {

    private final Queue<Card> deck;

    private Deck(Deque<Card> cards) {
        this.deck = new ArrayDeque<>(cards);
    }

    public static Deck createNewDeck() {
        List<Card> cards = Card.createNewCards();
        Collections.shuffle(cards);
        return new Deck(new ArrayDeque<>(cards));
    }

    public Card getCard() {
       return deck.poll();
    }
}
