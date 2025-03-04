import java.util.ArrayList;
import java.util.List;

public class Deck {

    private final List<Card> cards;

    public Deck() {
        cards = new ArrayList<>();
        init();
    }

    public Card pick() {
        Card card = cards.getLast();
        cards.removeLast();
        return card;
    }

    private void init() {
        for (CardNumber number : CardNumber.values()) {
            for (CardShape shape : CardShape.values()) {
                cards.add(new Card(number, shape));
            }
        }
    }
}
