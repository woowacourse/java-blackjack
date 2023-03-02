package domain;

import java.util.Objects;

public class Card {
    private final CardType type;
    private final CardNumber number;

    public Card(final CardType type, final CardNumber number) {
        this.type = type;
        this.number = number;
    }

    public CardType getType() {
        return type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Card card = (Card) o;
        return type == card.type && number == card.number;
    }

    public CardNumber getNumber() {
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
