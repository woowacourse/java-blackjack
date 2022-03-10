package blackjack.domain;

import java.util.Objects;

public class Card {

    private final CardPattern pattern;
    private final CardNumber number;

    public Card(final CardPattern pattern, final CardNumber number) {
        this.pattern = pattern;
        this.number = number;
    }

    public String getPattern() {
        return pattern.getPattern();
    }

    public int getNumber() {
        return number.getNumber();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Card card = (Card) o;
        return number == card.number && pattern == card.pattern;
    }

    @Override
    public int hashCode() {
        return Objects.hash(pattern, number);
    }

    @Override
    public String toString() {
        return "Card{" +
                "pattern=" + pattern +
                ", number=" + number +
                '}';
    }
}
