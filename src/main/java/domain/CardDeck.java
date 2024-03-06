package domain;

import java.util.Collections;
import java.util.Stack;

public class CardDeck {
    private final Stack<Card> cards;

    public CardDeck() {
        this.cards = initializeCards();
    }

    private Stack<Card> initializeCards() {
        Stack<Card> cards = new Stack<>();

        for (Denomination denomination : Denomination.values()) {
            addCards(cards, denomination);
        }
        return cards;
    }

    private void addCards(Stack<Card> cards, Denomination denomination) {
        for (Emblem emblem : Emblem.values()) {
            cards.push(new Card(denomination, emblem));
        }
    }

    public void shuffle() {
        Collections.shuffle(cards);
    }
}
