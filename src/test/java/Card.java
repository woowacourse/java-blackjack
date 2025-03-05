import java.util.Objects;

public class Card {

    private final Pattern pattern;
    private final CardNumber cardNumber;

    public Card(Pattern pattern, CardNumber cardNumber) {
        this.pattern = pattern;
        this.cardNumber = cardNumber;
    }

    @Override
    public boolean equals(final Object object) {
        if (object == null || getClass() != object.getClass()) {
            return false;
        }
        Card card = (Card) object;
        return pattern == card.pattern && cardNumber == card.cardNumber;
    }

    @Override
    public int hashCode() {
        return Objects.hash(pattern, cardNumber);
    }
}
