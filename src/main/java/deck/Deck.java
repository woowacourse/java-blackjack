package deck;

import java.util.Stack;

import card.Card;

public class Deck {
    private final Stack<Card> cards;

    public Deck(Stack<Card> cards) {
        this.cards = cards;
    }

    public Card drawCard() {
        return cards.pop();
    }
}
