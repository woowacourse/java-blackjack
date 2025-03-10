package domain.card;

public enum TrumpNumber {

    ACE(1, "A"),
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
    KING(10, "K"),
    QUEEN(10, "Q");

    private final int score;
    private final String value;

    TrumpNumber(int score, String value) {
        this.score = score;
        this.value = value;
    }

    public int getScore() {
        return score;
    }

    public String getValue() {
        return value;
    }
}
