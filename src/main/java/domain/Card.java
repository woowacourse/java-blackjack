package domain;

import java.util.Objects;

public record Card(
        CardNumberType cardNumberType,
        CardType cardType
) {

    public boolean isAce() {
        return this.cardNumberType.isEqualTo(CardNumberType.ACE);
    }

    public boolean isNotAce() {
        return !this.cardNumberType.isEqualTo(CardNumberType.ACE);
    }

    public int getDefaultNumber() {
        return cardNumberType.getDefaultNumber();
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
