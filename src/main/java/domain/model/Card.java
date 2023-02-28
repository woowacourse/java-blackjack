package domain.model;

import java.util.List;
import java.util.Objects;

public class Card {

    private final String name;
    private final List<Integer> scores;

    public Card(final String name, final List<Integer> scores) {
        this.name = name;
        this.scores = scores;
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
        return Objects.equals(name, card.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return name;
    }
}
