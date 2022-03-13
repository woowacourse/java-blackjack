package blackjack.domain.card;

import java.util.Objects;

public class Card {

    private final CardNumber cardNumber;
    private final Type type;

    public Card(CardNumber cardNumber, Type type) {
        this.cardNumber = cardNumber;
        this.type = type;
    }

    public CardNumber getCardNumber() {
        return cardNumber;
    }

    public Type getType() {
        return type;
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
        return cardNumber == card.cardNumber && type == card.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(cardNumber, type);
    }

    @Override
    public String toString() {
        return "Card{" +
                "number=" + cardNumber +
                ", type=" + type +
                '}';
    }
}
