package blackjack.domain.carddeck;

import java.util.Objects;

public class Card {

    private final Pattern pattern;
    private final Number number;

    public Card(Pattern pattern, Number number) {
        this.pattern = pattern;
        this.number = number;
    }

    public Pattern getPattern() {
        return pattern;
    }

    public Number getNumber() {
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
        return pattern == card.pattern && number == card.number;
    }

    @Override
    public int hashCode() {
        return Objects.hash(pattern, number);
    }
}
