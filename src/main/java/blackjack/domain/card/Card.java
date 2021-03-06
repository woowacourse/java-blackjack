package blackjack.domain.card;

import java.util.Objects;

public class Card {

    private final Type type;
    private final Denomination denomination;

    public Card(Type type, Denomination denomination) {
        this.type = type;
        this.denomination = denomination;
    }

    public Type getType() {
        return type;
    }

    public Denomination getDenomination() {
        return denomination;
    }

    public String findTypeName() {
        return type.getName();
    }

    public String findDenominationName() {
        return denomination.getName();
    }

    public int findDenominationValue() {
        return denomination.getNumber();
    }

    public boolean isAce() {
        return denomination == Denomination.ACE;
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
        return type == card.type && denomination == card.denomination;
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, denomination);
    }

}
