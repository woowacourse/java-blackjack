package blackjack.model;

import java.util.Objects;

public class Card {
    private final Shape shape;
    private final Score score;

    public Card(final Shape shape, final Score score) {
        this.shape = shape;
        this.score = score;
    }

    public boolean isAce() {
        return score.isAce();
    }

    public int getScoreValue() {
        return score.getValue();
    }

    public Score getScore() {
        return score;
    }

    public Shape getShape() {
        return shape;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Card card = (Card) o;
        return shape == card.shape && score == card.score;
    }

    @Override
    public int hashCode() {
        return Objects.hash(shape, score);
    }
}
