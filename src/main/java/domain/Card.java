package domain;

import type.Letter;
import type.Shape;

import java.util.Objects;

public class Card {

    private final Shape shape;
    private final Letter letter;

    public Card(Shape shape, Letter letter) {
        this.shape = shape;
        this.letter = letter;
    }

    public String getShapeName() {
        return shape.getName();
    }

    public int getLetterScore() {
        return letter.getScore();
    }

    public String getLetterExpression() {
        return letter.getExpression();
    }

    public boolean isAce() {
        return letter == Letter.ACE;
    }

    @Override
    public boolean equals(Object object) {
        if (getClass() != object.getClass()) {
            return false;
        }

        Card card = (Card) object;
        return this.shape == card.shape
                && this.letter == card.letter;
    }

    @Override
    public int hashCode() {
        return Objects.hash(shape, letter);
    }

}
