package blackjack.domain.card;

public enum Value {

    ACE(1, 11),
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
    private final int maxScore;

    Value(int minScore, int maxScore) {
        this.minScore = minScore;
        this.maxScore = maxScore;
    }

    Value(int score) {
        this(score, score);
    }

    public boolean isAce() {
        return this == ACE;
    }

    public int getMinScore() {
        return minScore;
    }

    public int getMaxScore() {
        return maxScore;
    }
}
