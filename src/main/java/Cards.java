import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Cards {

    private final List<Card> cards;

    public Cards() {
        List<Card> cards = initializeCards();
        // TODO: shuffle을 통제할 수 있는 Random 필요!
        Collections.shuffle(cards, new Random());
        this.cards = cards;
    }

    private static List<Card> initializeCards() {
        List<Card> cards = new ArrayList<>();
        for (Value value : Value.values()) {
            for (Shape shape : Shape.values()) {
                cards.add(new Card(value.getValue(), shape.getShape()));
            }
        }
        return cards;
    }

    public boolean contains(final Card card) {
        return cards.contains(card);
    }
}
