package domain;

import java.util.Stack;

public class Deck {
    private final Stack<Card> deck;

    private Deck(Stack<Card> deck) {
        this.deck = deck;
    }

    public static Deck from(ShuffleStrategy shuffleStrategy) {
        Stack<Card> deck = new Stack<>();
        for (Shape shape : Shape.values()) {
            makeCard(deck, shape);
        }
        return new Deck(shuffleStrategy.shuffle(deck));
    }

    private static void makeCard(Stack<Card> deck, Shape shape) {
        for (Value value : Value.values()) {
            deck.add(new Card(value.getName() + shape.getShape(), value.getValue()));
        }
    }
}
