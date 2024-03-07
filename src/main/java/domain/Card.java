package domain;

import domain.constants.Score;
import domain.constants.Shape;
import java.util.Objects;

public class Card {
    private final Score score;
    private final Shape shape;

    public Card(Score score, Shape shape) {
        this.score = score;
        this.shape = shape;
    }

    public boolean isAceCard() {
        return this.score.equals(Score.ACE);
    }

    public int getScore() {
        return score.getValue();
    }

    public String getName() {
        return score.getName();
    }

    public String getShape() {
        return shape.getName();
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
        return score == card.score && shape == card.shape;
    }

    @Override
    public int hashCode() {
        return Objects.hash(score, shape);
    }
}
