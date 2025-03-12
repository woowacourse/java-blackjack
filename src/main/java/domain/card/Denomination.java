package domain.card;

public enum Denomination {
    ACE(11),
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

    private final int score;

    Denomination(final int score) {
        this.score = score;
    }

    public int getScore() {
        return score;
    }
}
