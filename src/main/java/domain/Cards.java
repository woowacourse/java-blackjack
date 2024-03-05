package domain;

import java.util.Collection;
import java.util.Collections;
import java.util.Stack;

public class Cards {

    private final Stack<Card> cards;

    public Cards(final Collection<Card> cards) {
        this.cards = new Stack<>();
        this.cards.addAll(cards);
    }

    public Card draw() {
        return cards.pop();
    }

    public void shuffle(){
        Collections.shuffle(cards);
    }
}
