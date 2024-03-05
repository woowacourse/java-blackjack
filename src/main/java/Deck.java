import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Deck {
    List<Card> cards;
    public Deck() {
        cards = new ArrayList<>();
        init();
    }

    private void init() {
        for (Shape shape : Shape.values()) {
            Arrays.stream(Rank.values())
                    .forEach(rank -> cards.add(new Card(shape, rank)));
        }
    }

    public Card draw(int index) {
         return cards.remove(index);
    }

    public List<Card> getCards() {
        return cards;
    }
}
