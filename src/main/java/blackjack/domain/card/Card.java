package blackjack.domain.card;

import java.util.Objects;

public final class Card {

    private final Shape shape;
    private final Number number;

    private Card(final Shape shape, final Number number) {
        this.shape = shape;
        this.number = number;
    }

    public static Card of(final Shape shape, final Number number) {
        return new Card(shape, number);
    }

    public String getCardName() {
        return String.format("%s%s", number.getSymbol(), shape.getName());
    }

    public int getScore() {
        return number.getScore();
    }

    public Number getNumber() {
        return number;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final Card card = (Card) o;
        return shape == card.shape && number == card.number;
    }

    @Override
    public int hashCode() {
        return Objects.hash(shape, number);
    }
}
