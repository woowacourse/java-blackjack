package blackjack.domain.card;

import java.util.Collections;
import java.util.Stack;

public class Deck {

    private final Stack<Card> cards;

    public Deck() {
        cards = creatDeck();
    }

    public void shuffle() {
        Collections.shuffle(cards);
    }

    public Card draw() {
        return cards.pop();
    }

    public Stack<Card> creatDeck(){
        Stack<Card> cards = new Stack<>();
        for (Type type : Type.values()) {
            for (Denomination denomination : Denomination.values()) {
                cards.push(new Card(type, denomination));
            }
        }
        return cards;
    }

}
