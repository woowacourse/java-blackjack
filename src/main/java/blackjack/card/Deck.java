package blackjack.card;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Deck {

    private final Queue<Card> cards;

    Deck(List<Card> cards) {
        this.cards = new LinkedList<>(cards);
    }

    public static Deck createShuffledFullDeck() {
        List<Card> fullCards = Card.getFullCards();
        Collections.shuffle(fullCards);
        return new Deck(fullCards);
    }

    public Card draw() {
        if (cards.isEmpty()) {
            throw new IllegalStateException("[ERROR] 덱이 비어있습니다.");
        }
        return cards.poll();
    }
}
