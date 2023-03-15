package blackjack.domain.card;

import java.util.Objects;

abstract public class Card {

    protected final Pattern pattern;
    protected final String value;

    public Card(Pattern pattern, String value) {
        validateValue(value);
        this.pattern = pattern;
        this.value = value;
    }

    protected void validateValue(String value) {}

    public abstract int getValue();

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Card card = (Card) o;
        return pattern == card.pattern && Objects.equals(value, card.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pattern, value);
    }

    public String getCardValueAndPattern() {
        return value + pattern.getName();
    }
}
