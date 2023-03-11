package domain.card;

public enum CardValue {
    TWO(2, 2),
    THREE(3, 3),
    FOUR(4, 4),
    FIVE(5, 5),
    SIX(6, 6),
    SEVEN(7, 7),
    EIGHT(8, 8),
    NINE(9, 9),
    TEN(10, 10),
    JACK(10, 10),
    QUEEN(10, 10),
    KING(10, 10),
    ACE(11, 1);

    private final int score;
    private final int extraScore;

    CardValue(int score, int extraScore) {
        this.score = score;
        this.extraScore = extraScore;
    }

    public boolean isAce() {
        return this == ACE;
    }

    public int getScore() {
        return score;
    }

    public int getExtraScore() {
        return extraScore;
    }
}
