package domain.card;

import java.util.Collections;
import java.util.Stack;

public class Deck {
    private final Stack<Card> cards;

    private Deck(Stack<Card> cards) {
        this.cards = cards;
    }

    public static Deck create() {
        Stack<Card> cards = new Stack<>();
        for (Type type : Type.values()) {
            cards.addAll(createBySymbol(type));
        }
        return new Deck(cards);
    }

    private static Stack<Card> createBySymbol(Type type) {
        Stack<Card> cards = new Stack<>();
        for (Symbol symbol : Symbol.values()) {
            cards.add(new Card(symbol, type));
        }
        return cards;
    }

    private void shuffle() {
        Collections.shuffle(cards);
    }

    public Card handOutCard() {
        shuffle();
        return cards.pop();
    }

    boolean isEmpty() {
        return cards.isEmpty();
    }
}
