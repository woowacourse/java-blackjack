package blackjack.domain.card;

import java.util.Objects;

public final class Card {

    private final Number number;
    private final Pattern pattern;

    public Card(final Number number, final Pattern pattern) {
        this.number = number;
        this.pattern = pattern;
    }

    public int convertToBlackjackScore() {
        return number.convertNumberToBlackjackScore();
    }

    public boolean isAce() {
        return this.number == Number.ACE;
    }

    public String getCardInfo() {
        return "" + number.getState() + pattern.getName();
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
