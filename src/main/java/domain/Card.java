package domain;

import type.Letter;
import type.Shape;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Card {

    private static final Map<Shape, Map<Letter, Card>> CACHE = new HashMap<>();

    private final Shape shape;
    private final Letter letter;

    private Card(Shape shape, Letter letter) {
        this.shape = shape;
        this.letter = letter;
    }

    public static Card of(Shape shape, Letter letter) {
        return CACHE.computeIfAbsent(shape,
                ignored -> createMapAndPutCardIfAbsent(shape, letter))
                .computeIfAbsent(letter, ignored -> new Card(shape, letter));
    }

    private static Map<Letter, Card> createMapAndPutCardIfAbsent(Shape shape, Letter letter) {
        Map<Letter, Card> cardMapper = new HashMap<>();
        cardMapper.put(letter, new Card(shape, letter));
        return cardMapper;
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

    @Override
    public String toString() {
        return "Card{" +
                "shape=" + shape +
                ", letter=" + letter +
                '}';
    }

}
