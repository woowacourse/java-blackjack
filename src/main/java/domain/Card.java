package domain;

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

    public Letter getValue() {
        return letter;
    }

    public boolean isAce() {
        return letter == Letter.ACE;
    }

}
