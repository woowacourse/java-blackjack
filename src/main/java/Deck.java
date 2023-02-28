import java.util.Collections;
import java.util.Stack;

public class Deck {
    private final Stack<Card> shuffledDeck;

    public Deck() {
        Stack<Card> deck = new Stack<>();

        addAllCards(deck);
        Collections.shuffle(deck);

        this.shuffledDeck = deck;
    }

    private void addAllCards(Stack<Card> deck) {
        Shape[] shapes = Shape.values();

        for (Shape shape : shapes) {
            addCardsOfShape(deck, shape);
        }
    }

    private void addCardsOfShape(Stack<Card> deck, Shape shape) {
        Number[] numbers = Number.values();

        for (Number number : numbers) {
            deck.add(new Card(shape, number));
        }
    }

    public int size() {
        return shuffledDeck.size();
    }
}
