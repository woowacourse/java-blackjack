package domain;

import java.util.Collections;
import java.util.Stack;

class CardDeck {

    private final Stack<Card> initPool = new Stack<>();

    {
        init();
    }

    private void init() {
        for (Suit suit : Suit.values()) {
            initNumber(suit);
        }
    }

    private void initNumber(Suit suit) {
        for (Number number : Number.values()) {
            initPool.add(new Card(suit, number));
        }
    }

    public void shuffle() {
        Collections.shuffle(initPool);
    }

    public Card pick() {
        return initPool.pop();
    }
}
