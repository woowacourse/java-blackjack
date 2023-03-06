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

}
