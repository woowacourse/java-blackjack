import java.util.ArrayList;
import java.util.List;

public class Cards {

    private final List<Card> cards = new ArrayList<>();

    public void add(Card card) {
        cards.add(card);
    }

    public int calculateTotalPoint() {
        return cards.stream()
                .mapToInt(card -> card.getNumber().getPoint())
                .sum();
    }

    public Card draw() {
        return cards.removeLast();
    }
}
