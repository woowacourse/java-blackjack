import java.util.Collections;
import java.util.Stack;

public class Deck {
    private final Stack<Card> cardStack;

    public Deck() {
        cardStack = new Stack<>();
        Number[] numbers = Number.values();
        Shape[] shapes = Shape.values();
        for (Shape shape : shapes) {
            for (Number number : numbers) {
                cardStack.add(new Card(shape,number));
            }
        }

        Collections.shuffle(cardStack);
    }

    public int size(){
        return cardStack.size();
    }
}
