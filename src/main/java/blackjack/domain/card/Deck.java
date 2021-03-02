package blackjack.domain.card;

import java.util.Collections;
import java.util.Stack;

public class Deck {

    private final Stack<Card> cards;

    private Deck(Stack<Card> cards) {
        this.cards = cards;
    }

    public static Deck create(Stack<Card> cards) {
        Collections.shuffle(cards);
        return new Deck(cards);
    }
}
