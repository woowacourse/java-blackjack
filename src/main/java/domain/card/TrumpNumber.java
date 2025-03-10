package domain.card;

public enum TrumpNumber {

    TWO(2, "2"),
    THREE(3, "3"),
    FOUR(4, "4"),
    FIVE(5, "5"),
    SIX(6, "6"),
    SEVEN(7, "7"),
    EIGHT(8, "8"),
    NINE(9, "9"),
    TEN(10, "10"),
    ACE(1, 11, "A"),
    JACK(10, "J"),
    KING(10, "K"),
    QUEEN(10, "Q");

    private final int minScore;
    private final int maxScore;
    private final String value;

    TrumpNumber(int minScore, int maxScore, String value) {
        this.minScore = minScore;
        this.maxScore = maxScore;
        this.value = value;
    }

    TrumpNumber(int minScore, String value) {
        this.minScore = minScore;
        this.maxScore = minScore;
        this.value = value;
    }

    public int getMinScore() {
        return minScore;
    }

    public int getGapBetweenMinMax() {
        return maxScore - minScore;
    }

    public String getValue() {
        return value;
    }
}
