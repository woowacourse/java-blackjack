package domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

public class Deck extends Cards {
    private final Stack<Card> deck = new Stack<>();

    public Deck(final List<Card> cards) {
        super(cards);
        List<Card> copiedCards = new ArrayList<>(this.cards);
        Collections.shuffle(copiedCards);
        copiedCards.forEach(deck::push);
    }

    public Card draw() {
        validateEmpty();
        return deck.pop();
    }
}
