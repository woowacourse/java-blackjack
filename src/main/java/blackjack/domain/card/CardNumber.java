package blackjack.domain.card;

public enum CardNumber {
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
    KING(10, "K"),
    QUEEN(10, "Q"),
    JACK(10, "J");

    public static final int ACE_CONVERT_NUMBER = 10;
    private final int value;
    private final String number;

    CardNumber(final int value, final String number) {
        this.value = value;
        this.number = number;
    }

    public String getNumber() {
        return number;
    }

    public int getValue() {
        return value;
    }
}
