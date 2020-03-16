package blackjack.domain.card;

import java.util.Collections;
import java.util.Stack;

public class Deck {
    private Stack<Card> cards;

    public Deck() {
        create();
    }

    private void create() {
        this.cards = CardFactory.createCards();
        shuffle();
    }

    private void shuffle() {
        Collections.shuffle(this.cards);
    }

    public Card pop() {
        if (cards.isEmpty()) {
            create();
        }
        return cards.pop();
    }

    public int size() {
        return cards.size();
    }
}
