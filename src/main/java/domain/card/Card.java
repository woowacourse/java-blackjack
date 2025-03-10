package domain.card;

import java.util.Objects;

public class Card {

    private final TrumpNumber number;
    private final TrumpShape shape;

    private Card(TrumpNumber number, TrumpShape shape) {
        this.number = number;
        this.shape = shape;
    }

    public static Card of(final TrumpNumber number, final TrumpShape shape) {
        return new Card(number, shape);
    }

    public int getMinScore() {
        return number.getMinScore();
    }

    public boolean hasScoreGap() {
        return number.getGapBetweenMinMax() != 0;
    }

    public int getScoreGap() {
        return number.getGapBetweenMinMax();
    }

    public String getNumberValue() {
        return number.getValue();
    }

    public String getShapeValue() {
        return shape.getValue();
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || getClass() != object.getClass()) {
            return false;
        }
        Card card = (Card) object;
        return number == card.number && shape == card.shape;
    }

    @Override
    public int hashCode() {
        return Objects.hash(number, shape);
    }
}
