package domain.card;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

public class Card {

    // TODO: 캐싱에 대해 공부해보자!
    // TODO: 클래스 변수 vs 상수
    private static final Map<String, Card> cache = new ConcurrentHashMap<>(52);

    private final Value value;
    private final Shape shape;

    public Card(final String shape, final String value) {
        this.value = Value.of(value);
        this.shape = Shape.of(shape);
    }

    public static Card of(final Shape shape, final Value value) {
        return cache.computeIfAbsent(toKey(shape, value), ignored -> new Card(shape.getShape(), value.getValue()));
    }

    private static String toKey(final Shape shape, final Value value) {
        return shape.getShape() + value.getValue();
    }

    public int getDefaultScore() {
        return value.getDefaultScore();
    }

    public String getValue() {
        return value.getValue();
    }

    public String getShape() {
        return shape.getShape();
    }

    public boolean isAce() {
        return value.isAce();
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
        return value == card.value && shape == card.shape;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value, shape);
    }
}
