package blackjack.domain.card;

import java.util.Objects;

public class Card {

    private static final int ACE_MAX = 11;
    private static final int ACE_MIN = 1;

    private final Denomination denomination;
    private final Shape shape;

    private Card(Denomination denomination, Shape shape) {
        this.denomination = denomination;
        this.shape = shape;
    }

    public static Card of(Denomination denomination, Shape shape) {
        return new Card(denomination, shape);
    }

    public int calculateScore() {
        return denomination.getScore();
    }

    public boolean isAce() {
        return denomination.isAce();
    }

    public Denomination getDenomination() {
        return denomination;
    }

    public Shape getShape() {
        return shape;
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
        return denomination == card.denomination && shape == card.shape;
    }

    @Override
    public int hashCode() {
        return Objects.hash(denomination, shape);
    }

    public int getAceScore(int result) {
        if (result < ACE_MAX) {
            return ACE_MAX;
        }
        return ACE_MIN;
    }
}
