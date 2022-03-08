package blackjack.domain;

import java.util.Stack;

public class Deck {

    private final Stack<Card> cards;

    private Deck(final Stack<Card> cards) {
        this.cards = cards;
    }

    public static Deck init() {
        Stack<Card> cards = new Stack<>();
        for (Suit suit : Suit.values()) {
            for (Denomination denomination : Denomination.values()) {
                cards.push(new Card(suit,denomination));
            }
        }
        return new Deck(cards);
    }

    public int findDeckSize() {
        return cards.size();
    }
}
