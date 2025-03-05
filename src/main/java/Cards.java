import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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

    public Card extractCard() {
        return cards.removeLast();
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Cards cards1 = (Cards) o;
        return Objects.equals(cards, cards1.cards);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(cards);
    }
}
