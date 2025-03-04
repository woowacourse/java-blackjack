import java.util.Objects;

public class Card {
    private final CardType cardType;
    private final int value;

    public Card(CardType cardType, int value) {
        this.cardType = cardType;
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Card card = (Card) o;
        return value == card.value && cardType == card.cardType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(cardType, value);
    }

    @Override
    public String toString() {
        return "Card{" +
                "cardType=" + cardType +
                ", value=" + value +
                '}';
    }
}
