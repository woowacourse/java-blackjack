package domain.card;

public enum Number {
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
    private final String number;

    Number(int score, String number) {
        this.score = score;
        this.number = number;
    }

    public int getScore() {
        return score;
    }

    public String getNumber() {
        return number;
    }
}
