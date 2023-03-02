package domain.model;

import domain.type.Letter;
import domain.type.Suit;
import java.util.Objects;

public class Card {

    private final Suit suit;
    private final Letter letter;

    public Card(final Suit suit, final Letter letter) {
        this.suit = suit;
        this.letter = letter;
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
        return letter.getSign() + suit.getName();
    }

    public int getNumber() {
        return letter.getNumber();
    }
}
