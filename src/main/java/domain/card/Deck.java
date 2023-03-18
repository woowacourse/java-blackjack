package domain.card;

import domain.strategy.ShuffleStrategy;

import java.util.Stack;

public class Deck {
    private final Stack<Card> cards;
    
    private Deck(Stack<Card> cards) {
        this.cards = cards;
    }

    public static Deck from(ShuffleStrategy shuffleStrategy) {
        return new Deck(initializeCards(shuffleStrategy));
    }

    private static Stack<Card> initializeCards(ShuffleStrategy shuffleStrategy) {
        Stack<Card> cards = new Stack<>();
        for (Shape shape : Shape.values()) {
            initializeCardsByShape(cards, shape);
        }
    
        return shuffleStrategy.shuffle(cards);
    }

    private static void initializeCardsByShape(Stack<Card> cards, Shape shape) {
        for (Number number : Number.values()) {
            cards.push(new Card(shape, number));
        }
    }

    public Card draw() {
        return cards.pop();
    }
}
