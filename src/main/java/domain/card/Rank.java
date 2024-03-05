package domain.card;

public enum Rank {

    KING(10),
    QUEEN(10),
    JACK(10),
    TEN(10),
    NINE(9),
    EIGHT(8),
    SEVEN(7),
    SIX(6),
    FIVE(5),
    FOUR(4),
    THREE(3),
    TWO(2),
    ACE(1);

    private final int score;

    Rank(int score) {
        this.score = score;
    }

    public int getScore() {
        return score;
    }
}
