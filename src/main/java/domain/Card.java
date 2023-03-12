package domain;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import type.Letter;
import type.Shape;

public class Card {

    private final Shape shape;
    private final Letter letter;
    private static final Map<String, Card> CACHE = new HashMap<>();

    private Card(Shape shape, Letter letter) {
        this.shape = shape;
        this.letter = letter;
    }

    static {
        for (Shape shape : Shape.values()) {
            Arrays.stream(Letter.values())
                    .forEach(letter -> CACHE.put(toKeyString(shape, letter), new Card(shape, letter)));
        }
    }

    public static Card of(Shape shape, Letter letter) {
        return CACHE.get(toKeyString(shape, letter));
    }

    private static String toKeyString(Shape shape, Letter letter) {
        return shape.getName() + letter.getExpression();
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
