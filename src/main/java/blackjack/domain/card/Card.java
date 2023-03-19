package blackjack.domain.card;

import java.util.Objects;

public final class Card {

    final private Shape shape;
    final private Letter letter;

    private Card(final Shape shape, final Letter letter) {
        this.shape = shape;
        this.letter = letter;
    }

    public static Card of(final Shape shape, final Letter letter) {
        return new Card(shape, letter);
    }

    public boolean isAce() {
        return letter.isAce();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Card card = (Card) o;
        return shape == card.shape && letter == card.letter;
    }

    @Override
    public int hashCode() {
        return Objects.hash(shape, letter);
    }

    public String getCardName() {
        return letter.getName() + shape.getValue();
    }

    public int getValue() {
        return letter.getValue();
    }
}
