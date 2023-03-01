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

    public boolean isMatch(final Letter letter) {
        return this.letter.equals(letter);
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
        return suit == card.suit && letter == card.letter;
    }

    @Override
    public int hashCode() {
        return Objects.hash(suit, letter);
    }

    @Override
    public String toString() {
        return letter.getNumber() + suit.getName();
    }

    public int getNumber() {
        return letter.getNumber();
    }
}
