package blackjack.domain.card;

import java.util.Objects;

public final class Card {

    private final Number number;
    private final Suit suit;

    public Card(final Number number, final Suit suit) {
        this.number = number;
        this.suit = suit;
    }

    public int score() {
        return Number.scoreOf(number);
    }

    public boolean isAce() {
        return this.number == Number.ACE;
    }

    public String combineNumberAndPattern() {
        return number.getState() + suit.getName();
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Card card = (Card) o;
        return number == card.number && suit == card.suit;
    }

    @Override
    public int hashCode() {
        return Objects.hash(number, suit);
    }
}
