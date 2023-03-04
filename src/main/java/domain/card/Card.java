package domain.card;

import java.util.Objects;

public class Card {

    private final Suit type;
    private final Denomination number;

    public Card(final Suit type, final Denomination number) {
        this.type = type;
        this.number = number;
    }

    public Suit getType() {
        return type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Card card = (Card) o;
        return type == card.type && number == card.number;
    }

    public Denomination getNumber() {
        return number;
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, number);
    }

    @Override
    public String toString() {
        return "Card{" +
                "type=" + type +
                ", number=" + number +
                '}';
    }
}
