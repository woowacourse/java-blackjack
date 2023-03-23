package domain.card;

public enum Denomination {
    ACE(11, "A"),
    TWO(2, "2"),
    THREE(3, "3"),
    FOUR(4, "4"),
    FIVE(5, "5"),
    SIX(6, "6"),
    SEVEN(7, "7"),
    EIGHT(8, "8"),
    NINE(9, "9"),
    TEN(10, "10"),
    JACK(10, "J"),
    QUEEN(10, "Q"),
    KING(10, "K");

    private final int score;
    private final String denomination;

    Denomination(int score, String denomination) {
        this.score = score;
        this.denomination = denomination;
    }

    public int getScore() {
        return score;
    }

    public String getDenomination() {
        return denomination;
    }
}
