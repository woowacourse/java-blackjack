import java.util.List;
import java.util.Objects;

public class Card {

    private static final List<String> numbers = List.of("A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "Q", "K",
            "J");

    private final CardNumber number;
    private final CardShape shape;

    public Card(CardNumber number, CardShape shape) {
        this.number = number;
        this.shape = shape;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Card card = (Card) o;
        return Objects.equals(number, card.number) && Objects.equals(shape, card.shape);
    }

    @Override
    public int hashCode() {
        return Objects.hash(number, shape);
    }
}
