package blackjack.domain.card;

import java.util.Objects;

public class Card {

    private final CardNumber number;
    private final CardPattern pattern;

    public Card(final CardNumber number, final CardPattern pattern) {
        this.number = number;
        this.pattern = pattern;
    }

    public int getNumber() {
        return number.getNumber();
    }

    public String getCardName() {
        return number.getInitial() + pattern.getPattern();
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final Card card = (Card) o;
        return number == card.number && pattern == card.pattern;
    }

    @Override
    public int hashCode() {
        return Objects.hash(pattern, number);
    }

    @Override
    public String toString() {
        return "Card{" + getCardName() + '}';
    }
}
