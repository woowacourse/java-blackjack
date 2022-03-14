package blackjack.domain.card;

public enum CardNumber {

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

    private final int number;
    private final String originalName;

    CardNumber(int number, String originalName) {
        this.number = number;
        this.originalName = originalName;
    }

    public int getNumber() {
        return number;
    }

    public String getOriginalName() {
        return originalName;
    }
}
