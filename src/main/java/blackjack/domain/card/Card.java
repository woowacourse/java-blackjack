package blackjack.domain.card;

import java.util.Objects;

public final class Card {

    private final Number number;
    private final Pattern pattern;

    public Card(final Number number, final Pattern pattern) {
        this.number = number;
        this.pattern = pattern;
    }

    public int convertToScore() {
        return number.getScore();
    }

    public boolean isAce() {
        return this.number == Number.ACE;
    }

    public Number getCardNumber() {
        return number;
    }

    public Pattern getCardPattern() {
        return pattern;
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
}
