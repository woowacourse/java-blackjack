package domain;

import java.util.Objects;

public class Card {

    private final String name;
    private final int value;

    public Card(final String name, final int value) {
        this.name = name;
        this.value = value;
    }

    public int sum(final Card otherCard) {
        return this.value + otherCard.value;
    }

    public boolean isA() {
        return name.charAt(0) == 'A';
    }

    public Card copyCard(){
        return new Card(this.name,this.value);
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final Card card = (Card) o;
        return value == card.value && Objects.equals(name, card.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, value);
    }

    public String getName() {
        return name;
    }
}
