import java.util.ArrayList;
import java.util.List;

public class Deck {
    private final List<String> numbers = List.of(
            "A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "Q", "K", "J"
    );
    private final List<String> shapes = List.of("spade", "heart", "clover", "diamond");

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
        for (String number : numbers) {
            for (String shape : shapes) {
                cards.add(new Card(number, shape));
            }
        }
    }
}
