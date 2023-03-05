package domain.card;

import java.util.Objects;

public class Card {
    private final String name;
    private final int value;

    private Card(final String name, final int value) {
        this.name = name;
        this.value = value;
    }

    public static Card of(Suit suit, Rank rank) {
        String name = rank.getName() + suit.getShape();
        int value = rank.getValue();
        return new Card(name, value);
    }

    public String getName() {
        return name;
    }

    public int getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Card card = (Card)o;
        return value == card.value && Objects.equals(name, card.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, value);
    }
}
