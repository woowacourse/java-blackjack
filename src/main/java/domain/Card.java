package domain;

import java.util.Objects;
import type.Letter;
import type.Shape;

public class Card {

    private final Shape shape;
    private final Letter letter;

    public Card(Shape shape, Letter letter) {
        this.shape = shape;
        this.letter = letter;
    }

    public Shape getShape() {
        return shape;
    }

    public Letter getLetter() {
        return letter;
    }

    public Score getScore() {
        return letter.getScore();
    }

    public boolean isAce() {
        return letter == Letter.ACE;
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
        return shape == card.shape && letter == card.letter;
    }

    @Override
    public int hashCode() {
        return Objects.hash(shape, letter);
    }

}
