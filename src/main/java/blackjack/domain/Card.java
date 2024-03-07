package blackjack.domain;

import java.util.Objects;

public class Card {

    private final Value value;
    private final Shape shape;

    public Card(Value value, Shape shape) {
        this.value = value;
        this.shape = shape;
    }

    public int getMinScore() {
        return value.getMinScore();
    }

    public int getMaxScore() {
        return value.getMaxScore();
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || getClass() != object.getClass()) {
            return false;
        }
        Card card = (Card) object;
        return value == card.value && shape == card.shape;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value, shape);
    }

    public Value getValue() {
        return value;
    }

    public Shape getShape() {
        return shape;
    }

    // TODO 외부 class로 분리 할 것인지 논의, 접근 제어자 논의
    public enum Value {
        ACE(1),
        TWO(2),
        THREE(3),
        FOUR(4),
        FIVE(5),
        SIX(6),
        SEVEN(7),
        EIGHT(8),
        NINE(9),
        TEN(10),
        JACK(10),
        QUEEN(10),
        KING(10);

        private final int minScore;

        Value(int minScore) {
            this.minScore = minScore;
        }

        public int getMinScore() {
            return minScore;
        }

        public int getMaxScore() {
            if (this == ACE) {
                return minScore + 10;
            }
            return minScore;
        }
    }

    public enum Shape {
        SPADE, DIAMOND, HEART, CLOVER
    }
}
