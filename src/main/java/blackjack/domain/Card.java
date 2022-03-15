package blackjack.domain;

import java.util.Objects;

public class Card {
    private final String name;
    private final int number;

    private Card(String name, int number) {
        this.name = name;
        this.number = number;
    }

    public static Card generateCard(String name, int number) {
        return new Card(name, number);
    }

    public String getName() {
        return name;
    }

    public int getNumber() {
        return number;
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
        return Objects.equals(name, card.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
