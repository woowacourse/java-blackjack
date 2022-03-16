package blackJack.domain.Card;

import java.util.*;

public class Card {
    private static final int TOTAL_CARDS_SIZE = 52;
    private static final Map<String,Card> CACHE = new ArrayList<>(TOTAL_CARDS_SIZE);

    private final Shape shape;
    private final Number number;

    static {
        for (Shape shape : Shape.values()) {
            for (Number number : Number.values()) {
                CACHE.put(createKey(shape, number), new Card(shape, number));
            }
        }
    }

    public Card(Shape shape, Number number) {
        this.shape = shape;
        this.number = number;
    }

    public static Card valueOf(Shape shape, Number number) {
        return CACHE.get(createKey(shape, number));
    }

    private static String createKey(Shape shape, Number number) {
        return shape.getShapeName() + number.getDenomination();
    }

    public Shape getShape() {
        return shape;
    }

    public Number getNumber() {
        return number;
    }

    public boolean isAce() {
        return number.isAce();
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
        return shape == card.shape && number == card.number;
    }

    @Override
    public int hashCode() {
        return Objects.hash(shape, number);
    }
}
