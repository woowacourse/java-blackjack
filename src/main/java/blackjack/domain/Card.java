package blackjack.domain;

public class Card {
    private final Value value;

    public Card(Value value, Shape shape) {
        this.value = value;
    }

    public int getMinScore() {
        return value.getMinScore();
    }

    public int getMaxScore() {
        return value.getMaxScore();
    }

    // TODO 외부 class로 분리 할 것인지 논의
    enum Value {
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

    enum Shape {
        SPADE, DIAMOND, HEART, CLOVER;
    }
}
