package domain.card;

import java.util.Objects;

public class Card {

    public static final String NULL_ERROR_MESSAGE = "[ERROR] 카드 값이나 모양에는 null이 들어갈 수 없습니다.";

    private final Value value;
    private final Shape shape;

    public Card(final Value value, final Shape shape) {
        validateNull(value, shape);
        this.value = value;
        this.shape = shape;
    }

    private static void validateNull(Value value, Shape shape) {
        if (value == null || shape == null) {
            throw new IllegalArgumentException(NULL_ERROR_MESSAGE);
        }
    }

    public int getScore(final int totalScore) {
        return value.getScore(totalScore);
    }

    public String getValue(){
        return value.getValue();
    }

    public String getShape(){
        return shape.getShape();
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
