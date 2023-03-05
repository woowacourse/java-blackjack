package domain.card;

public enum CardValue {
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
    KING(10),
    ACE(11,1);

    private final int score;
    private int extraScore;

    CardValue(int score, int extraScore) {
        this.score = score;
        this.extraScore = extraScore;
    }

    CardValue(int score) {
        this.score = score;
    }

    public boolean isAce() {
        return this.equals(ACE);
    }

    public int getScore() {
        return score;
    }

    public int getExtraScore() {
        return extraScore;
    }
}
