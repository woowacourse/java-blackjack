package blackjack.domain.card;

import java.util.Collections;
import java.util.Stack;

public class Deck {

    private Stack<Card> cards;

    public Deck() {
        cards = CardFactory.create();
    }

    public void shuffle() {
        Collections.shuffle(cards);
    }

    public Card draw() {
        return cards.pop();
    }

}
