package blackjack.domain.card;

import java.util.Objects;

public class Card {

    private final Number number;
    private final Pattern pattern;

    public Card(final Number number, final Pattern pattern) {
        this.number = number;
        this.pattern = pattern;
    }

    public int convertToScore() {
        return Number.getScoreOf(number);
    }

    public boolean isAce() {
        return this.number == Number.ACE;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Card card = (Card) o;
        return number == card.number && pattern == card.pattern;
    }

    @Override
    public int hashCode() {
        return Objects.hash(number, pattern);
    }

    @Override
    public String toString() {
        return "" + number.getState() + pattern.getName();
    }
}
