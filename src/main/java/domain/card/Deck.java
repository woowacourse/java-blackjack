package domain.card;

import domain.strategy.ShuffleStrategy;

import java.util.Stack;

public class Deck {
    private final Stack<Card> cards;
    private final ShuffleStrategy shuffleStrategy;


    private Deck(Stack<Card> cards, ShuffleStrategy shuffleStrategy) {
        this.cards = cards;
        this.shuffleStrategy = shuffleStrategy;
    }

    public static Deck from(ShuffleStrategy shuffleStrategy) {
        return new Deck(initializeCards(), shuffleStrategy);
    }

    private static Stack<Card> initializeCards() {
        Stack<Card> cards = new Stack<>();
        for (Shape shape : Shape.values()) {
            initializeCardsByShape(cards, shape);
        }

        return cards;
    }

    private static void initializeCardsByShape(Stack<Card> cards, Shape shape) {
        for (Number number : Number.values()) {
            cards.push(new Card(shape, number));
        }
    }

    public Card draw() {
        Stack<Card> shuffledCards = this.shuffleStrategy.shuffle(cards);
        return shuffledCards.pop();
    }
}
