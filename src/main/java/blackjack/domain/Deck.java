package blackjack.domain;

import java.util.Collections;
import java.util.Stack;

public class Deck {

    private final Stack<Card> cards;

    private Deck(final Stack<Card> cards) {
        this.cards = cards;
    }

    public static Deck init() {
        Stack<Card> cards = new Stack<>();
        for (Suit suit : Suit.values()) {
            pushCard(cards, suit);
        }
        Collections.shuffle(cards);
        return new Deck(cards);
    }

    private static void pushCard(final Stack<Card> cards, final Suit suit) {
        for (Denomination denomination : Denomination.values()) {
            cards.push(new Card(suit, denomination));
        }
    }

    public Card draw() {
        return cards.pop();
    }

    public int size() {
        return cards.size();
    }
}
