package domain.card;

import java.util.Objects;

public record Card(
        CardNumberType cardNumberType,
        CardType cardType
) {

    public int getNumber() {
        return cardNumberType.getCardNumber();
    }

    @Override
    public boolean equals(Object object) {
        if (object == null || getClass() != object.getClass()) {
            return false;
        }
        Card card = (Card) object;
        return cardNumberType == card.cardNumberType && cardType == card.cardType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(cardNumberType, cardType);
    }
}
