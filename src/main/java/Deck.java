import java.util.ArrayList;
import java.util.List;

public class Deck {
    List<Card> cards;
    public Deck() {
        cards = new ArrayList<>();
        init();
    }

    private void init() {
        for (Shape shape : Shape.values()) {
            for (Rank rank : Rank.values()) {
                cards.add(new Card(shape, rank));
            }
        }
    }

    public Card draw(int index) {
        return cards.get(index);
    }

    public List<Card> getCards() {
        return cards;
    }
}
