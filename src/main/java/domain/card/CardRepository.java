package domain.card;

import domain.strategy.ShuffleStrategy;

import java.util.Stack;

public class CardRepository {
    private final Stack<Card> cards;
    private final ShuffleStrategy shuffleStrategy;


    private CardRepository(Stack<Card> cards, ShuffleStrategy shuffleStrategy) {
        this.cards = cards;
        this.shuffleStrategy = shuffleStrategy;
    }

    public static CardRepository create(ShuffleStrategy shuffleStrategy) {
        return new CardRepository(initializeCards(), shuffleStrategy);
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

    public int size() {
        return cards.size();
    }

    public Card findAnyOneCard() {
        Stack<Card> shuffledCards = this.shuffleStrategy.shuffle(cards);
        return shuffledCards.pop();
    }
}
