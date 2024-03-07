package blackjack.domain.card;

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
