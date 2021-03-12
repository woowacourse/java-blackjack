package blackjack.domain.carddeck;

import java.util.Objects;

public class Card {

    private final Pattern pattern;
    private final Number number;

    public Card(final Pattern pattern, final Number number) {
        this.pattern = pattern;
        this.number = number;
    }

    public boolean isAce() {
        return this.number.equals(Number.ACE);
    }

    public int getScore() {
        return number.getNumber();
    }

    public String getPatternName() {
        return pattern.getPattern();
    }

    public String getNumberName() {
        return number.getName();
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
        return pattern == card.pattern && number == card.number;
    }

    @Override
    public int hashCode() {
        return Objects.hash(pattern, number);
    }
}
